package com.cgi.financeplanner;

import com.cgi.financeplanner.services.DatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinanceplannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceplannerApplication.class, args);
    }



//    @Bean
//    public DatabaseService databaseService(){
//        return new DatabaseService();
//    }
}
