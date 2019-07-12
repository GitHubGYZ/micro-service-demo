package com.example.microservicesimpleconsumermovie;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.loadbalancer.IRule;
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
    private IRule iRule;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private FeignUserInterface feignUserInterface;


    @RequestMapping("/user/{id}")
    @HystrixCommand(fallbackMethod = "getUserDefault", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") //配置超时时间,快速失败，还有更多的配置
    })  //即便是执行了回退，不代表断路器打开。要达到断路器的阈值才会打开断路器，之后再进入半开模式，测试服务是否恢复
    public User getUser(@PathVariable Integer id, QueryDto queryDto) {
        System.out.println(queryDto.getMobile() + queryDto.getName());
        return this.restTemplate.getForObject("http://" + chooseService("micro-service-provider-user") + "/demo/user/" + id, User.class);
    }

    /**
     * @Description:
     * @Param: [id]
     * @Author: gengyuzhong
     * @Date: 2019/7/12
     */
    @RequestMapping("/user2/{id}")
    public User getUser2(@PathVariable Integer id) {
        return feignUserInterface.getUserByid(id);
    }


    public User getUserDefault(@PathVariable Integer id, QueryDto queryDto) {
        return new User();
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
