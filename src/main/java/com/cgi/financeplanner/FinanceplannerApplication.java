package com.cgi.financeplanner;

import com.cgi.financeplanner.services.DatabaseService;
import com.mongodb.MongoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class FinanceplannerApplication {


    @Bean
    public MongoDbFactory mongoDbFactory() {

        SimpleMongoDbFactory fac = null;
        fac = new SimpleMongoDbFactory(new MongoClient(), "finances");
        return fac;
    }

    @Bean
    public MongoOperations mongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(FinanceplannerApplication.class, args);
    }
}
