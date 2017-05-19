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
		if(this.generalInfo.length!=StaticData.getGeneralFormat().getSize() || this.pointsInfo.length!=StaticData.getPointsFormat().getSize() || this.penaltiesInfo.length!=StaticData.getPenaltiesFormat().getSize()) {
			throw new InvalidFormatException();
		}
	}
	
	
	
	/*
	 * WARNING: ONLY USE THIS METHOD FOR CHANGING WHAT THE FORMAT STORES
	 * Method for getting one of user specified match set's reference to statistics
	 * Requirements: name of set (String)
	 * Returns: statistics (Statistic[])
	 */
	public Statistic[] getDataSetReference(String name) throws CouldNotFindException {
		switch(name) {
			case("generalInfo"): {
				return generalInfo;
			}
			case("pointsInfo"): {
				return pointsInfo;
			}
			case("penaltiesInfo"): {
				return penaltiesInfo;
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Note: Should be used as default over getDataSetReference()
	 * Method for getting one of user specified match set's reference to statistics
	 * Requirements: name of set (String)
	 * Returns: statistics (Statistic[])
	 */
	public Statistic[] getDataSetCopy(String name) throws CouldNotFindException {
		Statistic[] copyOf;
		switch(name) {
			case("generalInfo"): {
				copyOf = new Statistic[generalInfo.length];
				for(int x=0;x<generalInfo.length;x++) {
					copyOf[x] = generalInfo[x].clone();
				}
				return copyOf;
			}
			case("pointsInfo"): {
				copyOf = new Statistic[pointsInfo.length];
				for(int x=0;x<pointsInfo.length;x++) {
					copyOf[x] = pointsInfo[x].clone();
				}
				return copyOf;			
			}
			case("penaltiesInfo"): {
				copyOf = new Statistic[penaltiesInfo.length];
				for(int x=0;x<penaltiesInfo.length;x++) {
					copyOf[x] = penaltiesInfo[x].clone();
				}
				return copyOf;			
			}
		}
		throw new CouldNotFindException();
	}
	
	
	
	/*
	 * Method for returning user specified data from this match
	 * Requirements: format (Format)
	 * 				 data name (String)
	 * Returns: data (Statistic)
	 */
	public Statistic getData(Format form, String dataName) throws CouldNotFindException {
		for(Statistic stat:form.getStatisticsReference()) {
			if(stat.getName().equals(dataName)) return stat.clone();
		}
		throw new CouldNotFindException();
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
