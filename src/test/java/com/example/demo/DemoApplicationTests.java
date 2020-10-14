package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.*;
import com.example.demo.hessian.SayHelloHessian;
import com.example.demo.mapper.TaskConvertor;
import org.apache.commons.codec.language.bm.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {


    class Task{
        LinkedHashMap attribute = new LinkedHashMap();

        public void setAttribute(LinkedHashMap attribute) {
            this.attribute = attribute;
        }

        public Task(String phone, int age, String name) {
            this.phone = phone;
            this.age = age;
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        private String phone;

        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }


    @Test
    void shieldPhone(){
        List<Task> exportList = new ArrayList<>();
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("0571-1234223",12,"张三"));
        List<Task> task = shieldPhoneByList(exportList);
        task.forEach(
                System.out::println
        );
    }

    private <T> List<T>  shieldPhoneByList(List<T> list){
        if(list != null && list.isEmpty()){
            return list;
        }
        if (list != null && list.get(0) instanceof Task) {
            List<Task> taskList = (List<Task>) list;
            List<Task> collect = taskList.stream().peek(
                    s -> s.setPhone(phoneUtil(s.getPhone()))
            ).collect(Collectors.toList());
            return (List<T>) collect;
        }
        return list;
    }

    private String phoneUtil(String phone){
        String regExp = "^1\\d{10}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(phone);
        if(!m.matches()){// 若判断为固话
          return phone.replaceAll("-\\w{4}","-****");
        }

        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
    }

 /*   public static String replace (String str,int n,String newChar){
        String s1="";
        String s2="";
        try{
            s1=str.substring(0,n-1);
            s2=str.substring(n-1+newChar.length(),str.length());
            return s1+newChar+s2;
        }catch(Exception ex){
            return str;
        }
    }*/


    @Test
    void contextLoads() {
        UserPo userPo = new UserPo("奔驰", 5, UserPo.CarType.SEDAN);

        UserDo userDo = TaskConvertor.INSTANCE.po2do(userPo);

        System.err.println(userDo);
    }


    @Test
    public void sendMessage(){
        List<String> shieldPhone = new ArrayList<String>();
        shieldPhone.add("1");
        shieldPhone.add("2");
        shieldPhone.add("3");
        shieldPhone.add("4");
        List<String> oneList1 = new ArrayList<String>();
        oneList1.add("2");
        oneList1.add("3");
        oneList1.add("4");
        oneList1.add("5");


        System.err.println(oneList1.toString());
    }

    private String sendMessage(SendMessage sendMessage) {
        System.err.println("sendDingTalkMessage body is :" + JSON.toJSONString(sendMessage));
        //对staffId相同的进行去重
        ArrayList<Receiver> receivers = sendMessage.getReceiver().parallelStream().collect(collectingAndThen(
                toCollection(() -> new TreeSet<>(comparing(Receiver::getStaffId))), ArrayList::new));
        sendMessage.setReceiver(receivers);
        String s = HttpRequestUtil.httpPost("http://47.98.255.79:8002" + "/api/message/v1/normal/send", JSON.toJSONString(sendMessage));
        return s;
    }



    @Test
    public void hessian(){
        // 服务器暴露出的地址
        String url = "http://localhost:8081/helloHessian.do";

        // 客户端接口，需与服务端对象一样
        try {
            SayHelloHessian helloHessian = HessianProxyFactoryUtil.getHessianClientBean(SayHelloHessian.class,url);
            String msg =  helloHessian.SayHello("你好");

            System.out.println(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Test
    public void testt(){
        List<Task> exportList = new ArrayList<>();
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.add(new Task("18771632488",12,"张三"));
        exportList.forEach(
                s->{
                    LinkedHashMap<Object, Object> objectObjectLinkedHashMap = new LinkedHashMap<>();
                    objectObjectLinkedHashMap.put("1","2");
                    Class<?>[] cla = new Class[]{LinkedHashMap.class};

                    reflection(s, s.getClass(), "setAttribute", cla, objectObjectLinkedHashMap);
                }
        );

    }


    public static Object reflection(Object object, Class<?> cclass, String methodName, Class<?>[] paramclasses, Object... args) {
        try {
            Method method = cclass.getDeclaredMethod(methodName, paramclasses);
            return method.invoke(object, args);
        } catch (Exception var6) {
            var6.printStackTrace();
            return null;
        }
    }


}
