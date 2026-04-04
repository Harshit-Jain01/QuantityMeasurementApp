package com.app.quantitymeasurement.auth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.app.quantitymeasurement")
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.app.quantitymeasurement.repository")
@EntityScan(basePackages = "com.app.quantitymeasurement.entity")
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
