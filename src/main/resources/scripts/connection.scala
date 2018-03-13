import org.mongodb.scala.MongoClient


try {
val client:MongoClient=MongoClient("mongodb://localhost:27017")
  def liste = client.listDatabaseNames()
  liste.foreach(item => println(item.toString))
}catch {
  case ex: Exception => print(ex.getStackTrace)
}

println("Hello world!")
