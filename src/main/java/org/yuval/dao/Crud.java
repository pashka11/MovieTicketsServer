package org.yuval.dao;

import org.bson.Document;

import java.util.List;

/**
 * interface for all the data access objects
 * Create
 * Read
 * Update
 * Delete
 */
public interface Crud {
    enum status {
        OK, invalid_parameter, invalid_document, duplicate_id
    }
    boolean create(Object obj);
    Document read(String id);
    List<Document> readAll();
    boolean update(Document document);
    boolean drop(String id);
    boolean dropAll();
    String insertValidation(Document document);
}


