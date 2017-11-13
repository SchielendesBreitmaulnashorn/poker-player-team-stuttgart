package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "1.1";

    public static int betRequest(JsonElement request) {
    	JsonObject gameState = request.getAsJsonObject();
    	int currentByIn = gameState.get("current_buy_in").getAsInt();
    	int minimumRaise = gameState.get("minimum_raise").getAsInt();
    	JsonArray players = gameState.get("players").getAsJsonArray();
    	int ourStack = getOurStack(players);
    	int dealer = gameState.get("dealer").getAsInt();
    	int firstPlayer = (dealer+1)%(players.size());
    	System.out.println("testlog:" + ourStack);
    	return ourStack;
//    	JsonArray communityCards = gameState.get("community_cards").getAsJsonArray();
//    	for (JsonElement eachCard : communityCards) {
//			JsonObject communityCard = eachCard.getAsJsonObject();
//			communityCard.get("rank").getAsString();
//			communityCard.get("suit").getAsString();
//		}
//        return currentByIn + minimumRaise + (int) (Math.random()*32.0d);
    }

	private static int getOurStack(JsonArray players) {
		for (JsonElement eachPlayer : players) {
			JsonObject player = eachPlayer.getAsJsonObject();
			JsonElement playerName = player.get("name");
			if (playerName.getAsString().equals("Team Stuttgart")) {
				return player.get("stack").getAsInt();
			}
		}
		return 0;
	}

    public static void showdown(JsonElement game) {
    }
}

