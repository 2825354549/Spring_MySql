package com.ly;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.ly.mysql.mapper")
public class SpringMySqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringMySqlApplication.class, args);
        System.out.println("helloworld");
    }
}
