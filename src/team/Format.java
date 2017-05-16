package team;

import java.io.Serializable;
import java.util.ArrayList;

import team.teamException.*;

public class Format implements Serializable{	//Dual Purpose Class
	/*
	 * Formats for the competition's layout are stored so all classes can access them
	 */
	public static Format generalFormat;
	public static Format pointsFormat;
	public static Format penaltiesFormat;
	
	/*
	 * Stores the user provided layout for this format object
	 */
	private Statistic[] format;
	
	
	
	/*
	 * Constructor for creating a format
	 * Requirements: statistics (ArrayList<Statistic>)
	 */
	public Format(ArrayList<Statistic> format) throws InvalidFormatException{
		System.out.println(format);
		
		if (format.size()>0) {
			Statistic[] tform = new Statistic[format.size()];
			for(int x=0;x<tform.length;x++) {
				tform[x] = format.get(x);
			}
			this.format = tform;
		}
		else throw new InvalidFormatException();
	}
	
	
	
	/*
	 * Method for getting the size of the format
	 * Returns: size (int)
	 */
	public int getSize() {
		return format.length;
	}
	
	
	
	/*
	 * WARNING: ONLY USE THIS METHOD FOR CHANGING WHAT THE FORMAT STORES
	 * Method for getting the format's reference to statistics
	 * Returns: statistics (ArrayList<Statistic>)
	 */
	public Statistic[] getStatisticsReference() {
		return format;
	}
	
	
	
	/*
	 * Note: Should be used as default over getStatisticsReference()
	 * Method for getting the format's statistics as a copy of the original
	 * Returns: statistics (ArrayList<Statistic>)
	 */
	public Statistic[] getStatisticsCopy() {
			Statistic[] copyOf = new Statistic[format.length];
			for(int x=0;x<format.length;x++) {copyOf[x] = format[x].clone();}
			return copyOf;
	}
	
	
	
	/*
	 * Method for getting the position of a user specified statistic
	 * Requirements: format (Format)
	 * 				 statistic name (String)
	 * Returns: position (int)
	 */
	public static int getStatisticPosition(Format format, String dataName) throws CouldNotFindException {
		for(int x=0;x<format.getStatisticsReference().length;x++) {
			if(format.getStatisticsReference()[x].getName().equals(dataName)) return x;
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Method for checking if a statistic has a valid name based on the specified format
	 * Requirements: format (Format)
	 * 				 statistic (Statistic)
	 * Returns: is good (boolean) 
	 */
	public boolean isValidStatistic(Format form, Statistic stat) {
		boolean isGood = false;
		for (Statistic s:form.getStatisticsReference()) {
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
		for (Statistic s:generalFormat.getStatisticsReference()) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		for (Statistic s:pointsFormat.getStatisticsReference()) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		for (Statistic s:penaltiesFormat.getStatisticsReference()) {
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
