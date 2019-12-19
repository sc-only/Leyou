package cn.itcast.service.controller;

import cn.itcast.service.client.UserClient;
import cn.itcast.service.pojo.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("customer/user")
//@DefaultProperties(defaultFallback = "fallbackMethod")
public class UserController {

    @Autowired
    private UserClient userClient;

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    private DiscoveryClient discoveryClient;

    @GetMapping
    @ResponseBody
//    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    @HystrixCommand
    public String queryById(@RequestParam("id") Long id){

//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
//        ServiceInstance instance = instances.get(0);
//        return this.restTemplate.getForObject("http://service-provider/user/" + id, String.class);
        return this.userClient.queryUserById(id).toString();
    }

//    public String fallbackMethod(){
//        return "服务器正忙，请求稍后再试";
//    }
}
