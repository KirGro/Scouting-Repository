package runner;

import java.io.Serializable;
import java.util.ArrayList;
import team.*;
import team.teamException.*;

public class AutoMatchRunner implements Serializable{
	private TeamDataBase tdb; //Stores the reference to the database so it can add data sets to it
  
  
  
	/*
	 * Constructor for AutoMatchRunner
	 * Requirements: database (TeamDataBase)
	 */
	public AutoMatchRunner(TeamDataBase tdb) {
		this.tdb = tdb;
	}
  
  
  
	/*
	 * Method for automagically adding a match to the database, unless team number is invalid
	 * Requirements: match (MatchResult)
	 */
	public void autoAddMatch(MatchResult mr) throws InvalidNameNumberException {
		try {
			tdb.getTeamReference(mr.getData(Format.generalFormat, "Team Number").getData()).addMatch(mr);
		} catch (CouldNotFindException cnfe) {
			try {
				tdb.addTeam(new Team(mr.getData(Format.generalFormat, "Team Number").getData(),new ArrayList<MatchResult>(){{add(mr);}}));
			} catch (CouldNotFindException cnfe2) {
				autoAddMatch(mr); 	//Recalls because its impossible for the code input string to be wrong
			}
		}
	}
}
