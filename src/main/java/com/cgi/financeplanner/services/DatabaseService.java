package com.cgi.financeplanner.services;

import com.cgi.financeplanner.models.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DatabaseService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MongoOperations template;

    public DatabaseService() {
    }

    public List<Transaction> ReturnAllTransactions() {

        BasicQuery query = new BasicQuery("{ 'name' : 'transaction' }");
        logger.info("{}", template.getCollection("transactions").getNamespace().toString());

        List<Transaction> list = null;
        try {
            list = template.find(query, Transaction.class);
        } catch (Exception e) {
            startMongoScript();
            list = template.find(query, Transaction.class);
        }
        logger.info("Hat in der collection insgesamt {} transactions gefunden", list.size());
       
        return list;
    }

    //TODO check db connection, execute connection script

    //TODO CRUD operations and validations of requests and reads


    //TODO read from db

    //TODO write to db

    public void insert(Transaction transaction) {
        try {
            transaction.setName("transaction");
            template.insert(transaction);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Could not insert transaction");
        }
    }

    //TODO update in db

    //TODO delete from db

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
