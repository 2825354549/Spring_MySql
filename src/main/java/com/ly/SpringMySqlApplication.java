package com.ly;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.ly.mysql.mapper")
public class SpringMySqlApplication {
    /**
     * 应用程序的主入口函数。
     *
     * @param args 命令行参数，传递给SpringApplication的运行方法。
     * @see SpringApplication#run(Class, String...)
     */
    public static void main(String[] args) {
        // 运行Spring应用程序
        SpringApplication.run(SpringMySqlApplication.class, args);


        // 打印"Hello World"信息
        System.out.println("helloworld");


    }
}

