package mw56_sb55.server.model;

import java.util.HashMap;
import java.util.Map;

public class GameManage {
	/**
	 * scores hash map recording team -> (team score).
	 */
	Map<String, Integer> scores;
	
	/**
	 * The winning team.
	 */
	private String winningTeam;
	
	/**
	 * The highest score.
	 */
	private int highestScore;
	
	/**
	 * The current score.
	 */
	private int curScore;
	
	/**
	 * Construct the game manager.
	 */
	public GameManage(){
		scores = new HashMap<String, Integer>();
		winningTeam = "";
		highestScore = 0;
		curScore = 0;
	}
	
	/**
	 * Add team
	 * @param teamName The name of the team.
	 */
	public void addTeam(String teamName){
		if(!scores.containsKey(teamName)){
			scores.put(teamName, 0);
		}
		
	}
	
	/**
	 * Add the score to the team
	 * @param teamName The name of the team.
	 * @param price The price of the selected transportation.
	 * @param time The time spent of the selected transportation.
	 */
	public void addScore2Team(String teamName, int price, int time){
		curScore = scores.get(teamName);
		int timeScore = (10 - time) * 10;
		int priceScore = 1700 - price;
		
		int addedScore = timeScore + priceScore;
		curScore += addedScore;
		curScore -= 100;
		scores.replace(teamName, curScore);
		
		if(curScore > highestScore){
			highestScore = curScore;
			winningTeam = teamName;
		}
	}
	
	/**
	 * Get the winning team.
	 * @return The winning team.
	 */
	public String getWinningTeam(){
		return winningTeam;
	}
	
	/**
	 * Get the winning score.
	 * @return The winning score.
	 */
	public int getWinningScore(){
		return this.highestScore;
	}
	
	/**
	 * Get a team score
	 * @param teamName The name of the team.
	 * @return The score of that team.
	 */
	public int getScoreByTeamName(String teamName){
		return scores.get(teamName);
	}
	
	/**
	 * Get the current score of a team.
	 * @param teamName The name of the team.
	 * @return The current score of the team.
	 */
	public int getCurScore(String teamName){
		return scores.get(teamName);
	}
}
