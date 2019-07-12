package com.example.microservicesimpleconsumermovie;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "MICRO-SERVICE-PROVIDER-USER")
public interface FeignUserInterface {

    @RequestMapping("/demo/user/{id}")
    User getUserByid(@PathVariable Integer id);


}
