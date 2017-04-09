package org.yuval.dao;

/**
 * this interface intention is to check if a document is in use of another document
 * Created by Yuval on 05-Apr-17.
 */
public interface UsageCheck {
    boolean isInUse(String id);
}
