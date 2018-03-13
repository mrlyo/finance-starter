package com.cgi.financeplanner.services;

import com.cgi.financeplanner.models.ViewModel;
import com.mongodb.MongoClient;
import com.mongodb.DBCollection;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static com.mongodb.client.model.Filters.eq;


@Service
public class DatabaseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MongoClient mongoClient;
    private MongoDatabase database;

    String connectionResult;


    public DatabaseService() {
    }


    public List<String> readDatabases() {
        List<String> dblist;
        String result;

        try {
            mongoClient = new MongoClient("localhost", 27017);
            dblist = mongoClient.getDatabaseNames();
        } catch (Exception e) {
            logger.info("Reading databases failed. Trying to connect to DB");

            try {
                result = connect();
                mongoClient = new MongoClient("localhost", 27017);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            dblist = mongoClient.getDatabaseNames();
        }
        mongoClient.close();
        return dblist;

    }

    public FindIterable<Document> ReturnAllTransactions() {

        MongoCollection<Document> collection;
        FindIterable<Document> findIterable;

            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("finances");
            collection = db.getCollection("transactions");
            logger.info("Hat collection {} gefunden", collection.getNamespace());
            findIterable = collection.find(eq("name", "transaction"));
            mongoClient.close();
            return findIterable;



    }

    //TODO check db connection, execute connection script

    //TODO CRUD operations and validations of requests and reads


    //TODO read from db

    //TODO write to db

    public void insert(ViewModel viewModel) {

        Document document = new Document("name", "transaction")
                .append("amount", viewModel.getAmount())
                .append("subject", viewModel.getSubject())
                .append("type", viewModel.getType());

        MongoCollection<Document> coll;
        try {
            mongoClient = new MongoClient("localhost", 27017);
            MongoDatabase db = mongoClient.getDatabase("finances");
            coll = db.getCollection("transactions");
            logger.info("Hat collection {} gefunden", coll.getNamespace());
            coll.insertOne(document);
            logger.info("Erfolgreiches Insert");
            mongoClient.close();
        } catch (Exception e) {
            logger.info("Fehler beim insert");
        }
    }

    //TODO update in db

    //TODO delete from db

    private String connect() {
        logger.info("Die DatabaseServicemethode connect() wurde aufgerufen");
        try {
            mongoClient = new MongoClient("localhost", 27017);
            mongoClient.getDatabaseNames();

        } catch (Exception ex) {
            logger.info("MongoTimeoutException");
            ex.printStackTrace();

            logger.info("Starting DB Connection Script 'startmongo.bat'");
            int status = startMongoScript();
            if (status == 1) {
                logger.info("MongoDB started");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("waiting");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                database = mongoClient.getDatabase("finances");
                connectionResult = "success";
            } else {
                logger.info("MongoDB script startup failed");
                connectionResult = "failed";
            }
        }
        mongoClient.close();
        return connectionResult;
    }

    public void disconnect() {

        mongoClient.close();
    }

    private int startMongoScript() {
        logger.info("starting MongoScript");
        int return_value;
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "startmongo.bat");
        File dir = new File("C:\\Users\\jannik.meier\\Documents\\financeator\\src\\main\\resources\\scripts");
        pb.directory(dir);
        try {
            pb.start();
        } catch (IOException e) {
            e.printStackTrace();
            return_value = 0;
            logger.info("executing script {}", return_value);

            return return_value;
        }
        return_value = 1;
        logger.info("executing script {}", return_value);
        return return_value;
    }
}
