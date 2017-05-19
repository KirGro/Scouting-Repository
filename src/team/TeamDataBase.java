package team;

import java.io.Serializable;
import java.util.*;

import team.teamException.*;

public class TeamDataBase implements Serializable, Clonable<TeamDataBase>{
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	
	private ArrayList<Team> teams;			//Stores all the Team references
	private int year;						//Details the year that the data came from
	private boolean autosortTeams = true;	//Determines whether to autosort the list of team
	
	//private Format pointsFormat;	//Stores the format for how the competitions data is stored
	
	/*
	 * Stores the default formats for non-changing data
	 */
	//private Format generalFormat = StaticData.generalFormat;
	//private Format penaltiesFormat = StaticData.penaltiesFormat;
	
	
	/*
	 * Constructor for instantiating a TeamDataBase
	 * Requirements: year (int)
	 * 				 format (ArrayList<Statistic>)
	 */
	public TeamDataBase(int year, Format format) throws InvalidFormatException {
		this(year, format, new ArrayList<Team>());
	}
	
	
	
	/*
	 * Constructor for re-instantiating a TeamDataBase
	 * Requirements: year (int)
	 * 				 format (ArrayList<Statistic>)
	 * 				 teams (ArrayList<Team>)
	 */
	public TeamDataBase(int year, Format format, ArrayList<Team> teams) throws InvalidFormatException{
		this.year = year;
		StaticData.setFormat(StaticData.getGeneralFormat(), format, StaticData.getPenaltiesFormat());
		this.teams = teams;
	}
	
	
	
	/*
	 * WARNING: SHOULD ONLY BE USED TO MODIFY A TEAM'S DATA
	 * Method for returning a Team to the database
	 * Requires: team number (int)
	 * Returns: team (Team)
	 */
	public Team getTeamReference(int teamNumber) throws CouldNotFindException{
		for(Team t:teams) {
			if (t.getTeamNumber() == teamNumber) {
				return t;
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Note: Should be used as a default over getTeamReference()
	 * Method for returning a Team to the database
	 * Requires: team (Team)
	 * Returns: team (Team)
	 */
	public Team getTeamCopy(int teamNumber) throws CouldNotFindException{
		for(Team t:teams) {
			if (t.getTeamNumber() == teamNumber) {
				return t.clone();
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * WARNING: SHOULD ONLY BE USED TO MODIFY A TEAM'S DATA
	 * Method for returning a Team to the database
	 * Requires: team (Team)
	 * Returns: team (Team)
	 */
	public Team getTeamReference(Team team) throws CouldNotFindException{
		for(Team t:teams) {
			if (t.getTeamNumber() == team.getTeamNumber()) {
				return t;
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Note: Should be used as a default over getTeamReference()
	 * Method for returning a Team to the database
	 * Requires: team (Team)
	 * Returns: team (Team)
	 */
	public Team getTeamCopy(Team team) throws CouldNotFindException{
		for(Team t:teams) {
			if (t.getTeamNumber() == team.getTeamNumber()) {
				return t.clone();
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Method for finding the position of a team in the list of teams
	 * Requirements: teamNumber (int)
	 * Returns: position (int)
	 */
	public int getTeamPosition(int teamNumber) throws CouldNotFindException {
		for(int x=0;x<teams.size();x++) {
			if (teams.get(x).getTeamNumber()==teamNumber) return x;
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Method for adding a new Team to the database
	 * Requires: team (Team)
	 */
	public void addTeam(Team team) {
		teams.add(team);
		if(autosortTeams) teams.sort(null);
	}
	
	
	
	/*
	 * Checks if a team with the same number already exists and if:
	 * 		true: does not add the team to the list and throws a TeamAlreadyExistsException
	 * 		false: adds the team to the list as normal
	 * Requires: team (Team)
	 */
	public void checkBeforeAddTeam(Team team) {
		boolean exsists = false;
		for(Team t: teams) {
			if (t.getTeamNumber() == team.getTeamNumber()) {
				exsists = true;
			}
		}
		if (exsists == false) {
			addTeam(team);
			if(autosortTeams) teams.sort(null);
		}
		else if (team.getMatchesReference()!=null) {
			try {
				int place = getTeamPosition(team.getTeamNumber());
				for(MatchResult mr:team.getMatchesReference()) {
					teams.get(place).addMatch(mr);
				}
			} catch (CouldNotFindException e) {
				checkBeforeAddTeam(team);
			}
		}
	}
	
	
	/*
	 * 
	 */
	@Override
	public TeamDataBase clone() {
		try {
			ArrayList<Team> teamsTemp = new ArrayList<Team>(){{for(Team t:teams)add(t.clone());}};
			return new TeamDataBase(year, StaticData.getPointsFormat(), teamsTemp);
		} catch (InvalidFormatException ife) {
			return clone();
		}
	}
}
