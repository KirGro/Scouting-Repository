package teamAPI;

import java.util.ArrayList;
import java.io.Serializable;
import teamAPI.teamExceptionAPI.*;

public class Team implements Serializable, Comparable<Team>, Clonable<Team>{
	
	private boolean autosortMatches = true;		//Determines whether or not to sort the matches automagically
	private int teamNumber;						//Stores the teams number
	private String teamName;					//Stores the teams name
	private ArrayList<MatchResult> matches;		//Stores all the matches (MatchResult's) for the team
	
	
	
	/*
	 * Most basic constructor for creating a new Team with the team's number
	 * Requirements: team number (int)
	 */
	public Team(int teamNumber) throws InvalidNameNumberException{
		this.teamNumber = teamNumber;
		
		this.teamName = "No Team Name Set";
		
		matches = new ArrayList<MatchResult>();
	}
	
	
	
	/*
	 * Basic constructor for creating a new Team with the team's number that also adds a list of already entered matches
	 * Requirements: team number (int)
	 * 				 matches (ArrayList<MatchResult>)
	 */
	public Team(int teamNumber, ArrayList<MatchResult> matches) throws InvalidNameNumberException{
		this.teamNumber = teamNumber;
		
		this.teamName = "No Team Name Set";
		
		this.matches = matches;
	}
	
	
	
	/*
	 * Basic constructor for creating a new Team with the team's number and the team's name
	 * Requirements: team number (int)
	 * 				 matches (ArrayList<MatchResult>)
	 */
	public Team(int teamNumber, String teamName) throws InvalidNameNumberException{
		if(teamNumber>0)this.teamNumber = teamNumber;
		else throw new InvalidNameNumberException();
		
		if(teamName.length()<=30)this.teamName = teamName;
		else throw new InvalidNameNumberException();
		
		matches = new ArrayList<MatchResult>();
	}
	
	
	
	/*
	 * Constructor for creating a new Team with the team's number and the team's name that also adds a list of already entered matches
	 * Requirements: team number (int)
	 * 				 matches (ArrayList<MatchResult>)
	 */
	public Team(int teamNumber, String teamName, ArrayList<MatchResult> matches) throws InvalidNameNumberException{
		if(teamNumber>0)this.teamNumber = teamNumber;
		else throw new InvalidNameNumberException();
		
		if(teamName.length()<=30)this.teamName = teamName;
		else throw new InvalidNameNumberException();
		
		this.matches = matches;
	}
	
	
	
	/*
	 * Method for getting the team's number
	 * Returns: team number (int)
	 */
	public int getTeamNumber() {
		return teamNumber;
	}
	
	
	
	/*
	 * Method for getting the current name of the team
	 * Returns: team name (String)
	 */
	public String getTeamName() {
		return teamName;
	}
	
	
	
	/*
	 * Method for getting whether or not autosort is enabled based on whether or not autosort is enabled
	 * Returns: enabled (boolean)
	 */
	public boolean autosortEnabled() {
		return autosortMatches;
	}
	
	
	
	/*
	 * Method for turning on/off autosorting of matches based on the users input
	 * Requirements: enabled (boolean)
	 */
	public void setAutosortEnabled(boolean enabled) {
		autosortMatches = enabled;
		if(autosortMatches) matches.sort(null);
	}
	
	
	
	/*
	 *  Method for adding a data point in the form of a MatchResult to the end of matches
	 *  Requirements: A non-null MatchResult reference
	 */
	public void addMatch(MatchResult data) {
		matches.add(data);
	}
	
	
	
	/*
	 * Method for getting all of the team's matches
	 * Returns: matches (ArrayList<MatchResult>)
	 */
	public ArrayList<MatchResult> getMatches() {
		return matches;
	}
	
	
	/*
	 * Method for comparing two Team objects by their team number
	 * Returns: difference (int)
	 */
	@Override 
	public int compareTo(Team o) {
		return teamNumber-o.getTeamNumber();
	}
	
	
	/*
	 * Method for making a clone of the current Team
	 * Returns: clone (Team)
	 */
	@Override
	public Team clone() {
		try {
			ArrayList<MatchResult> matchesTemp = new ArrayList<MatchResult>(){{for(MatchResult mr:matches)add(mr.clone());}};
			Team clone = new Team(teamNumber, teamName, matchesTemp);
			return clone;
		} 
		catch(InvalidNameNumberException inne) {
			return clone();
		}
	}

}
