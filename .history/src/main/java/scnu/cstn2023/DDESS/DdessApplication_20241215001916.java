package scnu.cstn2023.DDESS;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DdessApplication {

	public static void main(String[] args) {
		SpringApplication.run(DdessApplication.class, args);
	}

}
