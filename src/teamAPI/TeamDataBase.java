package teamAPI;

import java.util.*;
import teamAPI.teamExceptionAPI.*;

public class TeamDataBase {
	private static final int CURRENT_YEAR = Calendar.getInstance().get(Calendar.YEAR);
	
	private ArrayList<Team> teams;			//Stores all the Team references
	private int year;						//Details the year that the data came from
	private boolean autosortTeams = true;	//Determines whether to autosort the list of team
	
	private Format pointsFormat;	//Stores the format for how the competitions data is stored
	
	/*
	 * Stores the default formats for non-changing data
	 */
	private Format generalFormat = new Format(new ArrayList<Statistic>(){{add(new Statistic("Team Number"));	//Any positive integer representing a team's number
																		  add(new Statistic("Alliance"));		//The color of the alliance the team was on (0=Red, 1=Blue)	
																		  add(new Statistic("Location"));		//Location of the event (working on a code...)
																		  add(new Statistic("Match Number"));	//Number of the match as an integer
																		  add(new Statistic("Outcome"));		//Did they Win/Tie/Lose the match (0=Lose, 1=Tie, 2=Win)
																		  add(new Statistic("Match Type"));}});	//The type of match (0=Qualifications, 1=Eliminations)
	private Format penaltiesFormat = new Format(new ArrayList<Statistic>(){{add(new Statistic("Stopped"));					//Was the robot stopped (0=No, 1=Yes)
																			add(new Statistic("Disabled"));					//Was the robot disabled (0=No, 1=Yes)
																			add(new Statistic("Red Carded"));				//Was the robot red carded (0=No, 1=Yes)
																			add(new Statistic("Disqualified"));				//Was the robot disqualified (0=No, 1=Yes)
																			add(new Statistic("Yellow Card #1"));			//Was the robot yellow carded (#1) (0=No, 1=Yes)
																			add(new Statistic("Yellow Card #2"));			//Was the robot yellow carded (#2) (0=No, 1=Yes)
																			add(new Statistic("Foul Number"));
																			add(new Statistic("Technical Foul Number"));}});
	
	
	/*
	 * Constructor for instantiating a TeamDataBase
	 * Requirements: year (int)
	 * 				 format (ArrayList<Statistic>)
	 */
	public TeamDataBase(int year, Format format) throws InvalidFormatException {
		this(year, format, new ArrayList<Team>());
		updateFormat();
	}
	
	
	
	/*
	 * Constructor for re-instantiating a TeamDataBase
	 * Requirements: year (int)
	 * 				 format (ArrayList<Statistic>)
	 * 				 teams (ArrayList<Team>)
	 */
	public TeamDataBase(int year, Format format, ArrayList<Team> teams) throws InvalidFormatException{
		this.year = year;
		this.pointsFormat = format;
		updateFormat();
		this.teams = teams;
	}
	
	
	
	/*
	 * Method for setting the generalInfo Format
	 * Requirements: format (Format)
	 */
	public void setGeneralFormat(Format generalFormat) {
		this.generalFormat = generalFormat;
		updateFormat();
	}
	
	
	
	/*
	 * Method for setting the pointsInfo Format
	 * Requirements: format (Format)
	 */
	public void setPointsFormat(Format pointsFormat) {
		this.pointsFormat = pointsFormat;
		updateFormat();
	}
	
	
	
	/*
	 * Method for setting the penaltieslInfo Format
	 * Requirements: format (Format)
	 */
	public void setPenaltiesFormat(Format penaltiesFormat) {
		this.penaltiesFormat = penaltiesFormat;
		updateFormat();
	}
	
	
	
	/*
	 * Method for getting the current format of generalInfo
	 * Requirements: none
	 */
	public Format getGeneralFormat() {
		return generalFormat;
	}
	
	
	
	/*
	 * Method for getting the current format of pointsInfo
	 * Requirements: none
	 */
	public Format getPointsFormat() {
		return pointsFormat;
	}
	
	
	
	/*
	 * Method for getting the current format of penaltiesInfo
	 * Requirements: none
	 */
	public Format getPenaltiesFormat() {
		return penaltiesFormat;
	}
	
	
	/*
	 * Method for updating the formats throughout the database
	 */
	private void updateFormat() {
		if(generalFormat==null) System.out.println("Help!");
		Format.setFormat(generalFormat, pointsFormat, penaltiesFormat); 
	}
	
	
	/*
	 * Method for returning a Team to the database
	 * Requires: team number (int)
	 * Returns: team (Team)
	 */
	public Team getTeam(int teamNumber) throws CouldNotFindException{
		for(Team t:teams) {
			if (t.getTeamNumber() == teamNumber) {
				return t;
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Method for returning a Team to the database
	 * Requires: team (Team)
	 * Returns: team (Team)
	 */
	public Team getTeam(Team team) throws CouldNotFindException{
		for(Team t:teams) {
			if (t.getTeamNumber() == team.getTeamNumber()) {
				return t;
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
		else if (team.getMatches()!=null) {
			try {
				int place = getTeamPosition(team.getTeamNumber());
				for(MatchResult mr:team.getMatches()) {
					teams.get(place).addMatch(mr);
				}
			} catch (CouldNotFindException e) {
				checkBeforeAddTeam(team);
			}
		}
	}
}
