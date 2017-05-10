package teamAPI;

import java.util.*;
import teamAPI.teamExceptionAPI.*;

public class Format {
	static Format generalFormat;
	static Format pointsFormat;
	static Format penaltiesFormat;
	
	private ArrayList<Statistic> format;
	
	
	public Format(ArrayList<Statistic> format) throws InvalidFormatException{
		if (format.size()>0) this.format = format;
		else throw new InvalidFormatException();
	}
	
	public int getSize() {
		return format.size();
	}
	
	public ArrayList<Statistic> getStatistics() {
		return format;
	}
	
	public static int getStatisticPosition(Format format, String dataName) throws CouldNotFindException {
		for(int x=0;x<format.getStatistics().size();x++) {
			if(format.getStatistics().get(x).getName().equals(dataName)) return x;
		}
		throw new CouldNotFindException();
	}
	
	public boolean isValidStatistic(Statistic stat) {
		boolean isGood = false;
		for (Statistic s:format) {
			if(s.getName().equals(stat.getName())) {
				isGood = true;
			}
		}
		return isGood;
	}
	
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
	
	public static void setFormat(Format generalFormat, Format pointsFormat, Format penaltiesFormat) {
		Format.generalFormat = generalFormat;
		Format.pointsFormat = pointsFormat;
		Format.penaltiesFormat = penaltiesFormat;
	}
	
}
