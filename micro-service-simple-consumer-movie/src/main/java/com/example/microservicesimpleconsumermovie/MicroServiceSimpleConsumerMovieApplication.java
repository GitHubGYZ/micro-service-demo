package com.example.microservicesimpleconsumermovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MicroServiceSimpleConsumerMovieApplication {
	//使用rest 协议访问 服务提供者  rest 是一种规范   不只是可以用json 也可以用xml 更可以用html做消息返回  相比于soap 简单
	@Bean
	public RestTemplate restTemplate(){
	return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceSimpleConsumerMovieApplication.class, args);
	}

}
