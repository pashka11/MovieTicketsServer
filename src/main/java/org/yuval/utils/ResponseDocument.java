package org.yuval.utils;

import org.bson.Document;

/**
 * Created by Yuval on 18-Apr-17.
 */
public interface ResponseDocument {
    /**
     * @param value
     * @return JSON document
     */
    Document docResponse(String value);
}
