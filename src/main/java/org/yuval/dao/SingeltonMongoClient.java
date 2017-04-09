package org.yuval.dao;

import com.mongodb.MongoClient;

/**
 * enables to create only one instance of this mongo client
 * to use: call the getInstance() method
 *
 */
public class SingeltonMongoClient {
    private static volatile SingeltonMongoClient instance;
    private static MongoClient client = new MongoClient();

    public static SingeltonMongoClient getInstance(){
        if (instance == null){
            synchronized (SingeltonMongoClient.class){
                if (instance  == null ){
                    instance = new SingeltonMongoClient();
                }
            }
        }
        return instance;
    }

    public static MongoClient getClient() {
        return client;
    }

    public static void setClient(MongoClient client) {
        SingeltonMongoClient.client = client;
    }
}
