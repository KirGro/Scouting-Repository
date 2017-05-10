package teamAPI;

import teamAPI.teamExceptionAPI.*;

public class MatchResult implements Comparable<MatchResult>, Clonable<MatchResult>{
	private Statistic[] generalInfo;
	private Statistic[] pointsInfo;
	private Statistic[] penaltiesInfo;
	
	public MatchResult(Statistic[] generalInfo, Statistic[] pointsInfo, Statistic[] penaltiesInfo) throws InvalidFormatException{
		this.generalInfo = generalInfo;
		this.pointsInfo = pointsInfo;
		this.penaltiesInfo = penaltiesInfo;
		if(generalInfo.length!=Format.generalFormat.getSize() || pointsInfo.length!=Format.pointsFormat.getSize() || penaltiesInfo.length!=Format.penaltiesFormat.getSize()) {
			throw new InvalidFormatException();
		}
	}
	
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
	
	@Override
	public int compareTo(MatchResult o) {
		try {
			return getDataAll("Match Number").getData()-o.getDataAll("Match Number").getData();
		} catch (CouldNotFindException e) {
			return compareTo(o);
		}
	}
	
	@Override
	public MatchResult clone() {
		try {
			return new MatchResult(generalInfo, pointsInfo,penaltiesInfo);
		} catch (InvalidFormatException e) {
			return clone();
		}
	}

}
