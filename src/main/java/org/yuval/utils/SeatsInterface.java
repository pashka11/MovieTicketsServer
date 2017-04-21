package org.yuval.utils;

import org.bson.Document;

/**
 * Created by Yuval on 19-Apr-17.
 */
public interface SeatsInterface {
    Integer[][] getSeatsFromShowInstanceDoc(Document document);
}
