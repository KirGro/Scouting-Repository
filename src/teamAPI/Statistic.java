package teamAPI;

import teamAPI.teamExceptionAPI.*;

public class Statistic implements Clonable<Statistic>{
	
	private String name;
	private int data;
	
	public Statistic(String name) {
		this.name = name;
		data = -1;
	}
	
	public Statistic(String name, int data) throws InvalidFormatException{
		this.name = name;
		this.data = data;
		if(!Format.isAnyValidStatistic(this)) throw new InvalidFormatException();
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public int getData() {
		return data;
	}
	
	@Override
	public Statistic clone() {
		try {
			return new Statistic(name, data);
		} catch (InvalidFormatException e) {
			return clone();
		}
	}
}
