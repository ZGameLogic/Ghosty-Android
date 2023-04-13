package com.zgamelogic.ghosty.services;

import com.zgamelogic.ghosty.data.Ghost;

import org.json.JSONObject;

import java.util.LinkedList;

public abstract class GhostyAPI {

    public LinkedList<Ghost> getGhosts(){
        return new LinkedList<Ghost>();
    }

    public LinkedList<String> getEvidences(){
        return new LinkedList<String>();
    }


}
