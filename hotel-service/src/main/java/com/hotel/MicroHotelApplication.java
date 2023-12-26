package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroHotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroHotelApplication.class, args);
	}

}
