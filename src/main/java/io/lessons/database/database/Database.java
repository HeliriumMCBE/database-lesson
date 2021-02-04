package io.lessons.database.database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import org.bson.Document;

@Getter
public class Database {

    public static MongoClient client;
    public static MongoDatabase database;
    public static MongoCollection<Document> collection;

    public static void connect() {
        ConnectionString connectionString = new ConnectionString("your connection sring");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .retryWrites(true)
                .build();

        client = MongoClients.create(settings);
        database = client.getDatabase("your database");
        collection = database.getCollection("your collection"); // Если такой коллекции нет, то она создастся
    }

    public static void disconnect() {
        if (client != null) client.close();
    }

    public static boolean exists(String username) {
        return collection.find(new Document("username", username)).first() != null;
    }

    public static void create(String username) {
        Document document = new Document("username", username).append("rank", "default");
        collection.insertOne(document);
    }

    public static void remove(String username) {
        if (exists(username)) {
            collection.deleteOne(new Document("username", username));
        }
    }

    public static Document get(String username) {
        return exists(username) ? collection.find(new Document("username", username)).first() : null;
    }
}
