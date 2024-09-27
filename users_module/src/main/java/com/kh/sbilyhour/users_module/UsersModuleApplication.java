package com.kh.sbilyhour.users_module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan({"com.kh.sbilyhour.common_module", "com.kh.sbilyhour.users_module"})
@EnableTransactionManagement
public class UsersModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsersModuleApplication.class, args);
    }

}
