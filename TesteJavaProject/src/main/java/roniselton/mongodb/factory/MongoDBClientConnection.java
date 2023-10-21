package roniselton.mongodb.factory;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MongoDBClientConnection {

    public static String USER = "default";
    public static  String SENHA = "default" ;
    static{

        try {
            Properties props = new Properties();
            props.load(new FileReader( "C:\\Java\\MongoDB\\config.properties" ));
            USER = props.getProperty("user");
            SENHA = props.getProperty("senha");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String connectionString = "mongodb+srv://"+USER+":"+SENHA+"@viagens.m55j4ub.mongodb.net/?retryWrites=true&w=majority";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

}
