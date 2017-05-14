package teamAPI;

import java.io.Serializable;
import java.util.ArrayList;
import teamAPI.teamExceptionAPI.*;

public class Format implements Serializable{	//Dual Purpose Class
	/*
	 * Formats for the competition's layout are stored so all classes can access them
	 */
	static Format generalFormat;
	static Format pointsFormat;
	static Format penaltiesFormat;
	
	/*
	 * Stores the user provided layout for this format object
	 */
	private ArrayList<Statistic> format;
	
	
	
	/*
	 * Constructor for creating a format
	 * Requirements: statistics (ArrayList<Statistic>)
	 */
	public Format(ArrayList<Statistic> format) throws InvalidFormatException{
		if (format.size()>0) this.format = format;
		else throw new InvalidFormatException();
	}
	
	
	
	/*
	 * Method for getting the size of the format
	 * Returns: size (int)
	 */
	public int getSize() {
		return format.size();
	}
	
	
	
	/*
	 * Method for getting the format's statistics
	 * Returns: statistics (ArrayList<Statistic>)
	 */
	public ArrayList<Statistic> getStatistics() {
		return format;
	}
	
	
	
	/*
	 * Method for getting the format's statistics as a copy of the original if wanted
	 * Requirements: copy (boolean)
	 * Returns: statistics (ArrayList<Statistic>)
	 */
	public ArrayList<Statistic> getStatistics(boolean copy) {
		if(copy) {
			ArrayList<Statistic> copyOf = new ArrayList<Statistic>(){{for(Statistic s:format)add(s.clone());}};
			return copyOf;
		}
		return format;
	}
	
	
	
	/*
	 * Method for getting the position of a user specified statistic
	 * Requirements: format (Format)
	 * 				 statistic name (String)
	 * Returns: position (int)
	 */
	public static int getStatisticPosition(Format format, String dataName) throws CouldNotFindException {
		for(int x=0;x<format.getStatistics().size();x++) {
			if(format.getStatistics().get(x).getName().equals(dataName)) return x;
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Method for checking if a statistic has a valid name based on the specified format
	 * Requirements: statistic (Statistic)
	 * Returns: is good (boolean) 
	 */
	public boolean isValidStatistic(Statistic stat) {
		boolean isGood = false;
		for (Statistic s:format) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		return isGood;
	}
	
	
	
	/*
	 * Method for checking if a statistic has a valid name in any of the static formats
	 * Requirements: statistic (Statistic)
	 * Returns: is good (boolean) 
	 */
	public static boolean isAnyValidStatistic(Statistic stat) {
		boolean isGood = false;
		for (Statistic s:generalFormat.getStatistics()) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		for (Statistic s:pointsFormat.getStatistics()) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		for (Statistic s:penaltiesFormat.getStatistics()) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		return isGood;
	}
	
	
	
	/*
	 * Method for setting the static formats
	 * Requirements: general format (Format)
	 * 				 points format (Format)
	 * 				 penalties format (Format)
	 */
	public static void setFormat(Format generalFormat, Format pointsFormat, Format penaltiesFormat) {
		Format.generalFormat = generalFormat;
		Format.pointsFormat = pointsFormat;
		Format.penaltiesFormat = penaltiesFormat;
	}
	
}
