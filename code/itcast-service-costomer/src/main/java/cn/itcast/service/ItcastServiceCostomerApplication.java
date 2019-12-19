package cn.itcast.service;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
@EnableFeignClients
public class ItcastServiceCostomerApplication {

//    @Bean
//    @LoadBalanced //开启负载均衡
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(ItcastServiceCostomerApplication.class, args);
    }

}
