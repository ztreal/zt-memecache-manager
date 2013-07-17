package com.zt.memcache.service;

import com.zt.memcache.domain.config.MemDto;
import com.zt.memcache.domain.config.MemcacheConfig;

/**
 * User: zhangtan
 * Date: 12-9-7
 * Time: 上午9:47
 */
public interface UserService {

    /**
     * 精准删除缓存
     * @param key
     */
    public void delByKey(String key);

    /**
     * 模糊删除文件
     * @param key
     */
    public void delByLike(String key);

    /**
     * 模糊查询
     * @param key
     */
    public MemDto queryByLike(String key);


    /**
     * 模糊查询
     */
    public MemDto queryByLike();

    /**
     * 添加服务器配置
     * @param ip
     * @param port
     */
    public void addServer(String ip,int port);

    /**
     * 显示所有配置文件中配置的服务器地址和端口
     * @return
     */
    public MemcacheConfig   queryServer();

    /**
     * 重置配置的memcache服务器
     */
    public void   resetServer();

    /**
     * 删除指定服务器
     * @param ip
     * @param port
     */
    public void delServer(String ip,int port);
}
