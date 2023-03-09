package com.myprojects.storemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class StoremanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoremanagerApplication.class, args);
	}

}
