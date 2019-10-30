package com.target.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ashwin
 *
 */
@SpringBootApplication(scanBasePackages = {"com.target.myretail.*"})
public class MyRetailApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MyRetailApplication.class, args);
	}

}
