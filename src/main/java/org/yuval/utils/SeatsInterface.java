package org.yuval.utils;

import org.bson.Document;

public interface SeatsInterface {
    Integer[][] getSeatsFromShowInstanceDoc(Document document);
}
