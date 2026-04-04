package com.app.quantitymeasurement.auth_service;

import com.app.quantitymeasurement.auth.AuthServiceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
	classes = AuthServiceApplication.class,
	properties = {
		"spring.datasource.url=jdbc:h2:mem:authservice;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
		"spring.datasource.driver-class-name=org.h2.Driver",
		"spring.datasource.username=sa",
		"spring.datasource.password=",
		"spring.jpa.hibernate.ddl-auto=create-drop",
		"spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
		"eureka.client.enabled=false",
		"spring.cloud.discovery.enabled=false"
	}
)
class AuthServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
