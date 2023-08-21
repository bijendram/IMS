package com.ims.claim.service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
//@EnableEurekaClient (can use this one also)
//@EnableDiscoveryClient (more generic and can be used with any service discovery mechanism supported by Spring Cloud, including Eureka, Consul, ZooKeeper, etc) 
//and @EnableEurekaClient(if your use @EnableEurekaServer) are two annotations used in Spring Boot applications to enable service discovery 
//with Eureka or other service discovery mechanisms.
public class ClaimServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimServiceApplication.class, args);
	}

}
