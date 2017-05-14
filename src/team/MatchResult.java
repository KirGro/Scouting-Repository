package team;

import java.io.Serializable;
import java.util.ArrayList;

import team.teamException.*;

public class MatchResult implements Serializable, Comparable<MatchResult>, Clonable<MatchResult>{
	private Statistic[] generalInfo;	//Stores the data (Statistic's) for general info
	private Statistic[] pointsInfo;		//Stores the data (Statistic's) for points info
	private Statistic[] penaltiesInfo;	//Stores the data (Statistic's) for penalties info
	
	
	
	/*
	 * 
	 */
	public MatchResult(Statistic[] generalInfo, Statistic[] pointsInfo, Statistic[] penaltiesInfo) throws InvalidFormatException{
		this.generalInfo = generalInfo;
		this.pointsInfo = pointsInfo;
		this.penaltiesInfo = penaltiesInfo;
		if(this.generalInfo.length!=Format.generalFormat.getSize() || this.pointsInfo.length!=Format.pointsFormat.getSize() || this.penaltiesInfo.length!=Format.penaltiesFormat.getSize()) {
			throw new InvalidFormatException();
		}
	}
	
	
	
	/*
	 * Method for returning user specified data from this match
	 * Requirements: data name (String)
	 * Returns: data (Statistic)
	 */
	public Statistic getDataAll(String dataName) throws CouldNotFindException {
		for(Statistic stat:generalInfo) {
			if(stat.getName().equals(dataName)) return stat.clone();
		}
		for(Statistic stat:pointsInfo) {
			if(stat.getName().equals(dataName)) return stat.clone();
		}
		for(Statistic stat:penaltiesInfo) {
			if(stat.getName().equals(dataName)) return stat.clone();
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MatchResult o) {
		try {
			return getDataAll("Match Number").getData()-o.getDataAll("Match Number").getData();
		} catch (CouldNotFindException e) {
			return compareTo(o);
		}
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public MatchResult clone() {
		try {
			ArrayList<Statistic> generalInfoTemp = new ArrayList<Statistic>(){{for(Statistic s:generalInfo)add(s.clone());}};
			ArrayList<Statistic> pointsInfoTemp = new ArrayList<Statistic>(){{for(Statistic s:pointsInfo)add(s.clone());}};
			ArrayList<Statistic> penaltiesInfoTemp = new ArrayList<Statistic>(){{for(Statistic s:penaltiesInfo)add(s.clone());}};
			return new MatchResult((Statistic[]) generalInfoTemp.toArray(), (Statistic[]) pointsInfoTemp.toArray(), (Statistic[]) penaltiesInfoTemp.toArray());
		} catch (InvalidFormatException e) {
			return clone();
		}
	}

}
