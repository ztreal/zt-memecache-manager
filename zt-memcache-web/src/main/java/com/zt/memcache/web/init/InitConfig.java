package com.zt.memcache.web.init;

import com.zt.memcache.domain.config.MemcacheConfig;
import com.zt.memcache.util.memcache.MemConstant;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * 初始化配置等.
 * User: zhangtan
 * Date: 13-7-15
 * Time: 上午11:33
 */
@Service
 public class InitConfig implements InitializingBean {

    public static MemcacheConfig memcacheConfig;

    public static MemcachedClient memcachedClient = null;

    @Override
    public void afterPropertiesSet()  {
//        Thread.currentThread().getContextClassLoader().getResource("domain.config.dat");
        FileInputStream fi = null;
        try {
//            fi = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("config.dat").toString().substring(6));
           //初始化memcache配置
            fi = new FileInputStream(MemConstant.ConfigDatName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            memcacheConfig = (MemcacheConfig)oi.readObject();

            //初始化memclient
//            MemcachedClientBuilder builder = new XMemcachedClientBuilder(memcacheConfig.getMemcacheList());
//            memcachedClient = builder.build();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }




}
