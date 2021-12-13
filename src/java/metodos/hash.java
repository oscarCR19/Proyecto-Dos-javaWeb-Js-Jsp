/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 *
 * @author oscar
 */
public class hash {
    public String getSHA256(String contra){
        String hash=null;
        try{
            MessageDigest digest= MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(contra.getBytes("utf8"));
            hash=String.format("%064x", new BigInteger(1,digest.digest()));
        }catch(Exception e){
            System.out.println(e);
    }
        return hash;
    }
}
