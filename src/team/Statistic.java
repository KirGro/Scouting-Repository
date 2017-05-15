package team;

import java.io.Serializable;

import team.teamException.*;

public class Statistic implements Serializable, Clonable<Statistic>{
	
	private String name;	//Stores the name of the data point
	private int data;		//Stores the data for the data point
	
	
	
	/*
	 * WARNING: ONLY TO BE USED FOR MAKING A GUIDE STATISTIC FOR A FORMAT
	 * Constructor used to create a format object
	 * Requirements: name of statistic (String)
	 */
	public Statistic(String name) {
		this.name = name;
		data = -1;
	}
	
	
	
	/*
	 * Constructor for creating a data point
	 * Requirements: name of data (String)
	 * 				 data (int)
	 * *See TeamDataBase for data codes*
	 */
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
