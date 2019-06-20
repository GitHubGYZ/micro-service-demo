package com.example.microservicesimpleconsumermovie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class MovieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        return this.restTemplate.getForObject("http://"+chooseService("micro-service-provider-user")+"/demo/user/" + id, User.class);
    }

    @RequestMapping("/serviceInfo")
    public List<ServiceInstance> showInfo() {
        //获取到用户微服务的ip和端口详情。具体api还是要参考api文档
        return this.discoveryClient.getInstances("micro-service-provider-user");
    }


    @RequestMapping("/log-instance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("micro-service-provider-user");
        System.out.println("============================");
        MovieController.LOGGER.info(serviceInstance.getInstanceId());
        System.out.println("============================");
    }


    public String chooseService(String servicename) {
        return this.loadBalancerClient.choose(servicename).getInstanceId();
    }


}
