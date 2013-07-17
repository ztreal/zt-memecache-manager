package com.zt.memcache.web.action;

import com.alibaba.fastjson.JSON;
import com.zt.memcache.domain.config.MemDto;
import com.zt.memcache.domain.config.MemcacheConfig;
import com.zt.memcache.domain.config.PortStatus;
import com.zt.memcache.service.UserService;
import com.zt.memcache.util.socket.SocketCheckUtil;
import com.zt.memcache.util.spring.BaseAction;
import com.zt.memcache.web.init.InitConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 首页action.
 * User: zhangtan
 * Date: 13-6-4
 * Time: 下午5:07
 */
@Controller
public class IndexAction extends BaseAction {
    @Resource
    private UserService userService;
    @Resource
    private InitConfig initConfig;

    @RequestMapping(value = "/index", method = {RequestMethod.GET,
            RequestMethod.POST})
    public ModelAndView index() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("memcache");
        return mav;
    }

    /**
     * 根据地址使用百度地图api查询坐标
     *
     * @param key 地址
     * @return 坐标
     */
    @RequestMapping(value = "/delBykey", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String getGeo(@RequestParam("key") String key) {
          userService.delByKey(key);
        return null;

    }

    /**
     * 根据地址使用百度地图api查询坐标
     *
     * @param key 地址
     * @return 坐标
     */
    @RequestMapping(value = "/delByLike", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String delByLike(@RequestParam("key") String key) {
        userService.delByLike(key);
        return null;

    }


    @RequestMapping(value = "/addServer", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String addServer(@RequestParam("ip") String ip,@RequestParam("port") int port) {
        userService.addServer(ip,port);
        initConfig.afterPropertiesSet();
        return null;
    }



    @RequestMapping(value = "/queryServer", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String queryServer() {
        MemcacheConfig memcacheConfig = userService.queryServer();
        String rs = JSON.toJSONString(memcacheConfig);
        log.info(rs);
        return rs;
    }

    @RequestMapping(value = "/resetServer", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String resetServer() {
        userService.resetServer();
        initConfig.afterPropertiesSet();
//        InitConfig.memcacheConfig = null;
        return null;
    }

    @RequestMapping(value = "/queryByLike", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String queryByLike(@RequestParam(value = "key",required=false) String key) {
        String rs = null;
        MemDto memDto = null;
        if(StringUtils.isBlank(key) ||key.equals("null")){
            memDto   = userService.queryByLike(); //todo 为什么不这么些不对呢 rs = JSON.toJSONString(userService.queryByLike(key)); 就报错
            rs = JSON.toJSONString(memDto);
        }else{
            memDto   = userService.queryByLike(key);
            rs = JSON.toJSONString(memDto);
        }
        return rs;
    }

    @RequestMapping(value = "/checkPort", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public String checkPort(@RequestParam("ip") String ip,@RequestParam("port") int port) {
        PortStatus portStatus =new PortStatus();
        String rs = "";
        if(SocketCheckUtil.checkOprtIsOk(ip, port)){
            rs = "0";
//            portStatus.setStatusCode(0);
//            portStatus.setStatusDesc("服务器状态正常");
        }else{
//            portStatus.setStatusCode(1);
//            portStatus.setStatusDesc("无法连接服务器");
            rs = "1";
        }
        return rs;
    }

    @RequestMapping(value = "/delServerByIp", method = {RequestMethod.GET,
            RequestMethod.POST})
    @ResponseBody
    public void delServerByIp(@RequestParam("ip") String ip,@RequestParam("port") int port) {
        userService.delServer(ip,port);

    }

}
