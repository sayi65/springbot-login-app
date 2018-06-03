package com.wordnet.community.dao.Utility;

import java.sql.Timestamp;

public class Utility {

    public Utility(){}

    public static Timestamp getNowTimestamp(){
       return new Timestamp(System.currentTimeMillis());
    }
}
