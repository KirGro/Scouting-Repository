package teamAPI;

import java.util.*;

import teamAPI.teamExceptionAPI.*;

public class Team implements Comparable<Team>, Clonable<Team>{
	
	private boolean autosortMatches = true;
	private int teamNumber;
	private String teamName;
	private ArrayList<MatchResult> matches;
	
	public Team(int teamNumber) throws InvalidNameNumberException{
		this.teamNumber = teamNumber;
		
		this.teamName = "No Team Name Set";
		
		matches = new ArrayList<MatchResult>();
	}
	
	
	public Team(int teamNumber, ArrayList<MatchResult> matches) throws InvalidNameNumberException{
		this.teamNumber = teamNumber;
		
		this.teamName = "No Team Name Set";
		
		this.matches = matches;
	}
	
	
	public Team(int teamNumber, String teamName) throws InvalidNameNumberException{
		if(teamNumber>0)this.teamNumber = teamNumber;
		else throw new InvalidNameNumberException();
		
		if(teamName.length()<=30)this.teamName = teamName;
		else throw new InvalidNameNumberException();
		
		matches = new ArrayList<MatchResult>();
	}
	
	
	
	public Team(int teamNumber, String teamName, ArrayList<MatchResult> matches) throws InvalidNameNumberException{
		if(teamNumber>0)this.teamNumber = teamNumber;
		else throw new InvalidNameNumberException();
		
		if(teamName.length()<=30)this.teamName = teamName;
		else throw new InvalidNameNumberException();
		
		this.matches = matches;
	}
	
	
	
	/*
	 * Returns the teams number
	 */
	public int getTeamNumber() {
		return teamNumber;
	}
	
	
	
	/*
	 * Returns the current name of the team
	 */
	public String getTeamName() {
		return teamName;
	}
	
	
	
	/*
	 * Returns true/false based on whether or not autosort is enabled
	 */
	public boolean autosortEnabled() {
		return autosortMatches;
	}
	
	
	
	/*
	 * Turns on/off autosorting of matches based on the users input
	 * 
	 */
	public void setAutosortEnabled(boolean enabled) {
		autosortMatches = enabled;
		if(autosortMatches) matches.sort(null);
	}
	
	
	
	/*
	 *  Adds a data point in the form of a MatchResult to the end of matches
	 *  Pre-Requisites: A non-null MatchResult reference
	 */
	public void addMatch(MatchResult data) {
		matches.add(data);
	}
	
	
	public ArrayList<MatchResult> getMatches() {
		return matches;
	}
	
	
	@Override 
	public int compareTo(Team o) {
		return teamNumber-o.getTeamNumber();
	}
	
	
	
	@Override
	public Team clone() {
		try {
			Team clone = new Team(teamNumber, teamName);
			return clone;
		} 
		catch(InvalidNameNumberException inne) {
			return clone();
		}
	}

}
