package org.yuval.utils;

import org.glassfish.jersey.internal.util.Base64;

/**
 * Created by Yuval on 16-Mar-17.
 * This class hanles encryption
 */
public class Encryption {

    /**
     * @param s string to encrypt
     * @return encrypted string
     */
    public static String encrypt(String s){
        return Base64.encodeAsString(s);
    }

    /**
     * @param s string to decrypt
     * @return decrypted string
     */
    public static String decrypt(String s){
        return Base64.decodeAsString(s);
    }
}
