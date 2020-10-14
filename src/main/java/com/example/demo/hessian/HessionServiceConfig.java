package com.example.demo.hessian;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;


import javax.annotation.Resource;

/**
 * TODO
 *
 * @author WuShuang
 * @version 1.0
 * @date 2020/10/12 1:55 下午
 */
@Configuration
public class HessionServiceConfig {

    @Resource
    private SayHelloHessian sayHelloHessian;

    /**
     * 1. HessianServiceExporter是由Spring.web框架提供的Hessian工具类，能够将bean转化为Hessian服务
     * 2. @Bean(name = "/helloHessian.do")加斜杠方式会被spring暴露服务路径,发布服务。
     * @return
     */
    @Bean("/helloHessian.do")
    public HessianServiceExporter exportHelloHessian()
    {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(sayHelloHessian);
        exporter.setServiceInterface(SayHelloHessian.class);
        return exporter;
    }
}
