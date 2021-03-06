package com.example.demo.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * http方法调用
 * create by yangcy
 */
public class HttpRequestUtil {
    private static Integer connectionRequestTimeout = 3000;
    private static Integer socketTimeOut = 30000;
    private static Integer connectTimeout = 30000;

    /**
     * post方法 参数必须为json格式
     * @param url
     * @param jsonContent
     * @return
     */
    public static String httpPost(String url,String jsonContent){
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectTimeout).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        StringEntity requestEntity = new StringEntity(jsonContent,"utf-8");
        httpPost.setEntity(requestEntity);
        try{
//            System.out.println("url:"+url);
//            System.out.println("jsonContent:"+jsonContent);
            response = httpClient.execute(httpPost,new BasicHttpContext());
            if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
                //System.out.println("response code is :"+response.getStatusLine().getStatusCode());
                return null;
            }
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String resultStr = EntityUtils.toString(entity,"utf-8");
                return resultStr;
            }else{
                //System.out.println("response.getEntity is null");
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            if (response != null) try {
                response.close();
            } catch (IOException e) {
                //System.out.println("close http connection failed");
                e.printStackTrace();
            }
        }
    }
}
