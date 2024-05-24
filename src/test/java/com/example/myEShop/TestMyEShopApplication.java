package com.example.myEShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestMyEShopApplication {


	public static void main(String[] args) {
		SpringApplication.from(MyEShopApplication::main).with(TestMyEShopApplication.class).run(args);
	}

}
