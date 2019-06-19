package com.example.microservicesimpleconsumermovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/demo")
public class UserController {
@Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/user/{id}")
   public User getUser(@PathVariable  Integer id){
       return this.restTemplate.getForObject("http://localhost:9100/demo/user/"+id,User.class);
   };
}
