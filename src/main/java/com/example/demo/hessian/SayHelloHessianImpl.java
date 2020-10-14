package com.example.demo.hessian;

import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author WuShuang
 * @version 1.0
 * @date 2020/10/12 1:53 下午
 */
@Component
public class SayHelloHessianImpl implements SayHelloHessian{

    @Override
    public String SayHello(String msg) {
        System.err.println("-----------进入hessian方法,客户端参数："+ msg +"--------------");

        return "服务端返回：hello hessian";
    }
}
