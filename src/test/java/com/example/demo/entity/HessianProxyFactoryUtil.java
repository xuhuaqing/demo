package com.example.demo.entity;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * TODO
 *
 * @author WuShuang
 * @version 1.0
 * @date 2020/10/12 4:15 下午
 */
public class HessianProxyFactoryUtil {
    /**
     *  获取调用端对象
     * @param clazz 实体对象泛型
     * @param url 客户端url地址
     * @param <T>
     * @return 业务对象
     */
    public static <T> T getHessianClientBean(Class<T> clazz,String url) throws Exception
    {

        // 客户端连接工厂,这里只是做了最简单的实例化，还可以设置超时时间，密码等安全参数
        HessianProxyFactory factory = new HessianProxyFactory();

        return (T)factory.create(clazz,url);
    }

}
