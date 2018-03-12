package com.cgi.financeplanner.services;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.net.UnknownHostException;


@Service
public class DatabaseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private  MongoClient mongoClient;

    public String connect(){
        logger.info("Die DB connection methode wurde aufgerufen");
        try {
            mongoClient  = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
           logger.info("Die Verbindung hat geklappt.");
            return "success";
        } catch ( UnknownHostException unknownHostException) {
            logger.info("Die DB connection ist fehlgeschlagen.");
            unknownHostException.printStackTrace();
            return "connection failed";
        } finally {

        }
    }
    //TODO check db connection, execute connection script

    //TODO CRUD operations and validations of requests and reads


    //TODO read from db

    //TODO write to db

    //TODO update in db

    //TODO delete from db
}
