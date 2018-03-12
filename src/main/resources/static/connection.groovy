@groovy.lang.Grab ('org.mongodb:mongodb-driver:3.6.3')
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI

println("start")
MongoClient mongoClient

try {
   mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))

   def dbs = mongoClient.getDatabaseNames()
    dbs.each {item -> println(item.toString())}

}catch (e){
    println("error, trying to start the mongodb service")
    //check if some process is on port 27017

    //if is not--> start up mongod
    def started = false
    try {
       print "net start MongoDB".execute().text
        println("successfully started mongoDB")
        started = true
    }catch (e2){
        started = false
        println("failed to start mongodb Service, please start manually")
    }

    print(started.toString())
    if(started){
        println("retrying to connect to mongoDB")
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"))

        def dbs = mongoClient.getDatabaseNames()
        dbs.each {item -> println(item.toString())}

    }




}
mongoClient.close()

