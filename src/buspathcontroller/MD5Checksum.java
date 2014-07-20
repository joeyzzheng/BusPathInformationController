/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buspathcontroller;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Zhaowei
 */
public class MD5Checksum {

    public String getMD5(String path){
        String result="";
        try {
            FileInputStream fis = new FileInputStream(path);
            MessageDigest md = MessageDigest.getInstance("MD5");
          
            //Using MessageDigest update() method to provide input
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while( (numOfBytesRead = fis.read(buffer)) != -1){
                md.update(buffer, 0, numOfBytesRead);
            }
            byte[] hash = md.digest();
            result = new BigInteger(1, hash).toString(16); //don't use this, truncates leading zero
        } 
        catch (IOException ex) {} 
        catch (NoSuchAlgorithmException ex) {}
        return result;
    }
}
