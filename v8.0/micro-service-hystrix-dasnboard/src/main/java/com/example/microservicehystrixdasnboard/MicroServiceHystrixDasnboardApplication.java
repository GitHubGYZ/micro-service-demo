package com.example.microservicehystrixdasnboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class MicroServiceHystrixDasnboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceHystrixDasnboardApplication.class, args);
	}

}
