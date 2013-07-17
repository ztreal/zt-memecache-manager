package com.zt.memcache.service.impl;


import com.zt.memcache.domain.config.MemDto;
import com.zt.memcache.domain.config.MemcacheConfig;
import com.zt.memcache.service.BaseService;
import com.zt.memcache.service.UserService;
import com.zt.memcache.util.memcache.MemConstant;
import com.zt.memcache.util.memcache.MemcacheHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * User: zhangtan
 * Date: 12-9-7
 * Time: 上午9:47
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {
    @Resource
    private MemcacheHelper memcacheHelper;

    @Override
    public void delByLike(String key) {
        try {
            memcacheHelper.delByLike(key);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    @Override
    public MemDto queryByLike(String key) {
        MemDto memDto = new MemDto();
        try {
            memDto.setMemDatLIst(memcacheHelper.queryByLike(key));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return   memDto;
    }
    @Override
    public MemDto queryByLike() {
        MemDto memDto = new MemDto();
        try {
            memDto.setMemDatLIst(memcacheHelper.queryByLike());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return   memDto;
    }



    @Override
    public void delByKey(String key) {
        try {
            memcacheHelper.delByKey(key);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Override
    public void addServer(String ip,int port) {
        //首先加载老配置
        MemcacheConfig   memcacheConfig =  queryServer();
        if(memcacheConfig == null){
              memcacheConfig = new MemcacheConfig();
        }
        try {
            FileOutputStream fs = new FileOutputStream(MemConstant.ConfigDatName);
            ObjectOutputStream oo = new ObjectOutputStream(fs);
            ArrayList<InetSocketAddress> memcacheLis = null;
            if(memcacheConfig.getMemcacheList()!=null){
                memcacheLis = memcacheConfig.getMemcacheList();
            }else{
                memcacheLis = new ArrayList<InetSocketAddress>();
            }
            InetSocketAddress inetSocketAddress = new InetSocketAddress(ip,port);
            memcacheLis.add(inetSocketAddress);
            memcacheConfig.setMemcacheList(memcacheLis);
            oo.writeObject(memcacheConfig);
            log.info("add memecache server to dat file is ok .ip "+ip+" port "+port);

            MemcacheHelper.memcacheConfig = memcacheConfig;
            log.info("MemcacheHelper.memcacheConfig cache  is flush .ip "+ip+" port "+port);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    @Override
    public void delServer(String ip,int port) {
        //首先加载老配置
        MemcacheConfig   memcacheConfig =  queryServer();
        if(memcacheConfig == null){
            memcacheConfig = new MemcacheConfig();
        }
        try {
            FileOutputStream fs = new FileOutputStream(MemConstant.ConfigDatName);
            ObjectOutputStream oo = new ObjectOutputStream(fs);
            ArrayList<InetSocketAddress>  newmemcacheLis  = null;
            if(memcacheConfig.getMemcacheList()!=null){
                ArrayList<InetSocketAddress> memcacheLis = memcacheConfig.getMemcacheList();
                newmemcacheLis = new ArrayList<InetSocketAddress>();
                for(InetSocketAddress inetSocketAddress : memcacheLis){
                     if((inetSocketAddress.getAddress().toString().indexOf(ip)>=0) && inetSocketAddress.getPort()==port){
                           continue;
                     }else{
                         newmemcacheLis.add(inetSocketAddress);
                     }
                }
            }else{
                newmemcacheLis = new ArrayList<InetSocketAddress>();
            }
            memcacheConfig.setMemcacheList(newmemcacheLis);
            oo.writeObject(memcacheConfig);
            log.info("add memecache server to dat file is ok .ip "+ip+" port "+port);

            MemcacheHelper.memcacheConfig = memcacheConfig;
            log.info("MemcacheHelper.memcacheConfig cache  is flush .ip "+ip+" port "+port);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public MemcacheConfig   queryServer(){
        FileInputStream fi = null;
        MemcacheConfig memcacheConfig = null;
        try {
            fi = new FileInputStream(MemConstant.ConfigDatName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            memcacheConfig = (MemcacheConfig)oi.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return memcacheConfig;
    }

    public void   resetServer(){
        FileInputStream fi = null;
        MemcacheConfig memcacheConfig = new MemcacheConfig();
        try {
            FileOutputStream fs = new FileOutputStream(MemConstant.ConfigDatName);
            ObjectOutputStream oo = new ObjectOutputStream(fs);
            oo.writeObject(memcacheConfig);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }  catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
