package com.nackademin.webshopbackend.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Ashkan Amiri
 * Date:  26/04/2021
 * Time:  15:34
 * Project: webshop-backend
 * Copyright: MIT
 * Class that is used to Encrypt passwords (Strings)
 */
public class Encrypt {

    /**
     * Method that takes a String and returns it encrypted as per MD5 standard.
     * @param password A String value from use input that will be Encrypted.
     * @return An encrypted String.
     */
    public static String getMd5(String password) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(password.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
