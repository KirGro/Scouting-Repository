package team;

import java.util.ArrayList;

import team.teamException.InvalidFormatException;

public class StaticData {
	/*
	 * Formats for the competition's layout are stored so all classes can access them
	 */
	private static Format generalFormat;
	private static Format pointsFormat;
	private static Format penaltiesFormat;
	
	static {
		try {
			generalFormat = new Format(new ArrayList<Statistic>(){{add(new Statistic("Team Number"));	//Any positive integer representing a team's number
																   add(new Statistic("Alliance"));		//The color of the alliance the team was on (0=Red, 1=Blue)	
																   add(new Statistic("Location"));		//Location of the event (working on a code...)
																   add(new Statistic("Match Number"));	//Number of the match as an integer
																   add(new Statistic("Outcome"));		//Did they Win/Tie/Lose the match (0=Lose, 1=Tie, 2=Win)
																   add(new Statistic("Match Type"));}});
			penaltiesFormat = new Format(new ArrayList<Statistic>(){{add(new Statistic("Stopped"));					//Was the robot stopped (0=No, 1=Yes)
																	 add(new Statistic("Disabled"));					//Was the robot disabled (0=No, 1=Yes)
																	 add(new Statistic("Red Carded"));				//Was the robot red carded (0=No, 1=Yes)
																	 add(new Statistic("Disqualified"));				//Was the robot disqualified (0=No, 1=Yes)
																	 add(new Statistic("Yellow Card #1"));			//Was the robot yellow carded (#1) (0=No, 1=Yes)
																	 add(new Statistic("Yellow Card #2"));			//Was the robot yellow carded (#2) (0=No, 1=Yes)
																	 add(new Statistic("Foul Number"));
																	 add(new Statistic("Technical Foul Number"));}});
		} catch (InvalidFormatException e) {}
	}
	
	

	
	
	/*
	 * Method for setting the generalInfo Format
	 * Requirements: format (Format)
	 */
	public static void setGeneralFormat(Format generalFormat) {
		StaticData.generalFormat = generalFormat;
	}
	
	
	
	/*
	 * Method for setting the pointsInfo Format
	 * Requirements: format (Format)
	 */
	public static void setPointsFormat(Format pointsFormat) {
		StaticData.pointsFormat = pointsFormat;
	}
	
	
	
	/*
	 * Method for setting the penaltieslInfo Format
	 * Requirements: format (Format)
	 */
	public static void setPenaltiesFormat(Format penaltiesFormat) {
		StaticData.penaltiesFormat = penaltiesFormat;
	}
	
	
	
	/*
	 * Method for getting the current format of generalInfo
	 * Requirements: none
	 */
	public static Format getGeneralFormat() {
		return StaticData.generalFormat;
	}
	
	
	
	/*
	 * Method for getting the current format of pointsInfo
	 * Requirements: none
	 */
	public static Format getPointsFormat() {
		return StaticData.pointsFormat;
	}
	
	
	
	/*
	 * Method for getting the current format of penaltiesInfo
	 * Requirements: none
	 */
	public static Format getPenaltiesFormat() {
		return StaticData.penaltiesFormat;
	}
	
	
	
	/*
	 * Method for setting the static formats
	 * Requirements: general format (Format)
	 * 				 points format (Format)
	 * 				 penalties format (Format)
	 */
	public static void setFormat(Format generalFormat, Format pointsFormat, Format penaltiesFormat) {
		StaticData.generalFormat = generalFormat;
		StaticData.pointsFormat = pointsFormat;
		StaticData.penaltiesFormat = penaltiesFormat;
	}
}
