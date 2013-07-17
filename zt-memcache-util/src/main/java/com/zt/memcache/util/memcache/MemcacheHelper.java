package com.zt.memcache.util.memcache;

import com.zt.memcache.domain.config.MemDat;
import com.zt.memcache.domain.config.MemcacheConfig;
import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: zhangtan
 * Date: 13-7-11
 * Time: 下午6:44
 */
@Service
public class MemcacheHelper implements InitializingBean {

    private static Log log = LogFactory.getLog(MemcacheHelper.class);

    public static MemcacheConfig memcacheConfig;


//    @Resource
//    private XMemcachedClientBuilder memcachedClientBuilder;

//    @Resource
//    private ArrayList<InetSocketAddress> memcacheList;


    public MemcacheHelper() {
    }

    public void delByLike(String arg) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        log.info("delete  memcache key by like ,key is " + arg);
        for (InetSocketAddress address : memcacheConfig.getMemcacheList()) {
            log.info("server is =" + address.getAddress() + " port is " + address.getPort());
            try {
                MemcachedClient memcachedClient = getOneIpMemClient(address);
                KeyIterator keyIterator = memcachedClient.getKeyIterator(address);
                for (; keyIterator.hasNext(); ) {
                    String key = keyIterator.next();
                    log.info("key=" + key);
                    if (key.indexOf(arg) > 0) {
                        memcachedClient.delete(key);
                        log.info("delete memecache key " + key + " sucess");
                    }
                }
            } catch (MemcachedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (TimeoutException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            log.info(address.getAddress());
        }
    }


    public List queryByLike(String arg) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        log.info("queryByLike  memcache key by like ,key is " + arg);
        List<MemDat> memDatLIst = new ArrayList<MemDat>();
        for (InetSocketAddress address : memcacheConfig.getMemcacheList()) {
            log.info("server is =" + address.getAddress() + " port is " + address.getPort());
            try {
                MemcachedClient memcachedClient = getOneIpMemClient(address);
                KeyIterator keyIterator = memcachedClient.getKeyIterator(address);
                for (; keyIterator.hasNext(); ) {
                    String key = keyIterator.next();
                    log.info("key=" + key);
                    if (key.indexOf(arg) > 0) {
                        MemDat memDat = new MemDat();
                        String object = memcachedClient.get(key.toString());
                        memDat.setMemKey(key);
                        memDat.setMemValue(object);
                        memDat.setIp(address.getAddress().toString());
                        memDat.setPort(address.getPort());
                        memDatLIst.add(memDat);
                        log.info("queryByLike memecache key " + key + " sucess");
                    }
                }
            } catch (MemcachedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (TimeoutException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            log.info(address.getAddress());
        }
        return memDatLIst;
    }

    public List queryByLike() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        log.info("queryByLike arg is null ");
        List<MemDat> memDatLIst = new ArrayList<MemDat>();
        for (InetSocketAddress address : memcacheConfig.getMemcacheList()) {
            log.info("server is =" + address.getAddress() + " port is " + address.getPort());
            try {
                MemcachedClient memcachedClient = getOneIpMemClient(address);
                KeyIterator keyIterator = memcachedClient.getKeyIterator(address);
                for (; keyIterator.hasNext(); ) {
                    MemDat memDat = new MemDat();
                    String key = keyIterator.next();
                    String object = null;
                    if (memcachedClient.get(key) == null) {
                        log.info("key=" + key + " valuse is null");
                    } else {
                        object = memcachedClient.get(key).toString();
                        log.info("key=" + key + " valuse is  " + object);
                    }
                    memDat.setMemKey(key);
                    memDat.setMemValue(object);
                    memDat.setIp(address.getAddress().toString());
                    memDat.setPort(address.getPort());
                    memDatLIst.add(memDat);
                    log.info("queryByLike memecache key " + key + " sucess");
                }
            } catch (MemcachedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (TimeoutException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            log.info(address.getAddress());
        }
        return memDatLIst;
    }

    public void delByKey(String key) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (InetSocketAddress address : memcacheConfig.getMemcacheList()) {
            try {
                MemcachedClient memcachedClient = getOneIpMemClient(address);
                KeyIterator keyIterator = memcachedClient.getKeyIterator(address);
                for (; keyIterator.hasNext(); ) {
                    log.info("key=" + key);
                    memcachedClient.delete(key);
                    log.info("delete memecache key " + key + " sucess");
                }
                ;
            } catch (MemcachedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (TimeoutException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            log.info(address.getAddress());
        }
    }


    private MemcachedClient getOneIpMemClient(InetSocketAddress inetSocketAddress) {
//        InetSocketAddress InetSocketAddress = new InetSocketAddress(ip,port);
        ArrayList<InetSocketAddress> memcacheList = new ArrayList<InetSocketAddress>();
        memcacheList.add(inetSocketAddress);
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(memcacheList);
//        builder.setCommandFactory(new BinaryCommandFactory());此协议不支持遍历
        MemcachedClient memcachedClient = null;
        try {
            memcachedClient = builder.build();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return memcachedClient;
    }


    public void afterPropertiesSet() throws Exception {
        //        Thread.currentThread().getContextClassLoader().getResource("domain.config.dat");
        FileInputStream fi = null;
        try {
            //            fi = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("config.dat").toString().substring(6));
            //初始化memcache配置
            fi = new FileInputStream(MemConstant.ConfigDatName);
            ObjectInputStream oi = new ObjectInputStream(fi);
            memcacheConfig = (MemcacheConfig) oi.readObject();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
