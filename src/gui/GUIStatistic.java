package gui;

import team.Statistic;

public class GUIStatistic extends Statistic {
	private int autoTele, pos;

	public GUIStatistic(String name, int autoTele, int pos) {
		super(name);
		this.autoTele = autoTele;
		this.pos = pos;
	}
	
	
	public Statistic makeStat() {
		return new Statistic(getName());
	}
	
	
	public int getAutoTele() {
		return autoTele;
	}
	
	
	
	public int getPos() {
		return pos;
	}
}
