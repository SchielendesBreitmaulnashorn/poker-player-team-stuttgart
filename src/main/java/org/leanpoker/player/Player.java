package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        return 1000;
       // return 100 + (int) (Math.random()*66.0d);
    }

    public static void showdown(JsonElement game) {
    }
}
