/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

/**
 *
 * @author Matti
 */
public class Hasher {
    
    public static String generateHash(String password){
        return Hashing.md5().hashString(password, Charsets.UTF_8).toString();
    }
    
}
