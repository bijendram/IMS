package com.ims.categorypolicyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CategoryPolicyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CategoryPolicyServiceApplication.class, args);
	}

}
