package com.example.microservicesimpleconsumermovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class UserController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        return this.restTemplate.getForObject("http://localhost:9100/demo/user/" + id, User.class);
    }

    @RequestMapping("/serviceInfo")
    public List<ServiceInstance> showInfo() {
    //获取到用户微服务的ip和端口详情。具体api还是要参考api文档
        return this.discoveryClient.getInstances("micro-service-provider-user");
    }



}
