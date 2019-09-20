package com.macro.vsearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.macro.vsearch")
@SpringBootApplication
public class VsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(VsearchApplication.class, args);
    }

}
