package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
    	JsonObject gameState = request.getAsJsonObject();
    	int currentByIn = gameState.get("current_buy_in").getAsInt();
    	int minimumRaise = gameState.get("minimum_raise").getAsInt();
        return currentByIn + minimumRaise + (int) (Math.random()*32.0d);
    }

    public static void showdown(JsonElement game) {
    }
}
