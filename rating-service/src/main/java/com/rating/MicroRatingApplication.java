package com.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicroRatingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroRatingApplication.class, args);
	}
	
	@Bean
	@LoadBalanced /*load balancer + we can use application name instead of host & port*/
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
