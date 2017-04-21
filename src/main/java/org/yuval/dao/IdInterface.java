package org.yuval.dao;

/**
 * this interface handles all the id related to ID
 */
public interface IdInterface {
    int getNextSequence(com.mongodb.client.MongoCollection collection);
    int randomId(com.mongodb.client.MongoCollection coll);
}
