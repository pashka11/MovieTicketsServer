package org.yuval.utils;

import org.bson.Document;

/**
 * Created by Yuval on 18-Apr-17.
 */
public interface ResponseDocument {
    /**
     * @param value String to put in the doc
     * @return document for response
     */
    Document docResponse(String value);
}
