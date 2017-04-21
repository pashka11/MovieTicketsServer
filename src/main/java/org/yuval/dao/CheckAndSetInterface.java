package org.yuval.dao;

import org.bson.Document;

/**
 * Check And Set Interface - handles up date an field in a document
 */
public interface CheckAndSetInterface {
    boolean checkAndSet(com.mongodb.client.MongoCollection<Document> coll, String parameter, Document document);
}
