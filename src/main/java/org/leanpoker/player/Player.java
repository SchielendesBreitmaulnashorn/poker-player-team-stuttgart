package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "1.4";

    public static int betRequest(JsonElement request) {
    	JsonObject gameState = request.getAsJsonObject();
    	int currentByIn = gameState.get("current_buy_in").getAsInt();
    	int minimumRaise = gameState.get("minimum_raise").getAsInt();
    	JsonArray players = gameState.get("players").getAsJsonArray();
    	int dealer = gameState.get("dealer").getAsInt();
    	int numPlayers = players.size();
    	int firstPlayer = (dealer+1)%(numPlayers);
    	int ourPlayer = gameState.get("in_action").getAsInt();
    	int distance = getDistance(firstPlayer, ourPlayer, numPlayers);
    	int ourStack = getOurStack(players);
    	int ourBet = getOurBet(players);
    	int ourMinimumRaise = currentByIn - ourBet + minimumRaise;
    	int ourCall = currentByIn - ourBet;
    	int round = gameState.get("round").getAsInt();
    	
    	if (ourStack > 4000) {
    		return 0;
    	}
    	if (distance == 0) {
    		switch (round) {
    		case 0: return gameState.get("small_blind").getAsInt();
    		case 1:
    		case 2:
    		case 3:
    			return ourMinimumRaise;
    		}
    	} else {
    		switch (round) {
    		case 0:
    		case 1:
    		case 2:
    		case 3:
    			return ourCall;
    		}
    	}
    	if (round == 4 && ourStack == 1000) {
    		return 1000;
    	}
    	return ourMinimumRaise;
//    	if (distance < 3) {
//    		return ourMinimumRaise;
//    	} else if (distance < 5) {
//    		return ourMinimumRaise + 100;
//    	} else {
//    		return ourStack;
//    	}
//    	System.out.println("state:" + "dealer=" + dealer + ", firstPlayer=" + firstPlayer + ", ourPlayer=" + ourPlayer + ", distance=" + distance);
//    	switch (distance) {
//    	case 0: 
//    		break;
//    	case 1: 
//    		break;
//    	case 2: 
//    		break;
//    	case 3: 
//    		break;
//    	case 4: 
//    		break;
//    	case 5: 
//    		break;
//    	}
//    	return 0;
//    	JsonArray communityCards = gameState.get("community_cards").getAsJsonArray();
//    	for (JsonElement eachCard : communityCards) {
//			JsonObject communityCard = eachCard.getAsJsonObject();
//			communityCard.get("rank").getAsString();
//			communityCard.get("suit").getAsString();
//		}
//        return currentByIn + minimumRaise + (int) (Math.random()*32.0d);
    }

	private static int getDistance(int firstPlayer, int ourPlayer, int numPlayers) {
		if (firstPlayer <= ourPlayer) {
			return ourPlayer - firstPlayer;
		} else {
			return (ourPlayer - firstPlayer) + numPlayers;
		}
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

	private static int getOurBet(JsonArray players) {
		for (JsonElement eachPlayer : players) {
			JsonObject player = eachPlayer.getAsJsonObject();
			JsonElement playerName = player.get("name");
			if (playerName.getAsString().equals("Team Stuttgart")) {
				return player.get("bet").getAsInt();
			}
		}
		return 0;
	}

    public static void showdown(JsonElement game) {
    }
}

