package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

import runner.*;
import team.*;
import team.teamException.*;

public class AutoGeneratingGUI {
	private static int year = Calendar.getInstance().get(Calendar.YEAR);
	private static TeamDataBase tdb;
	private static GUI gui;
	private final static File prefs = new File("preferences.txt"); 
	
	public static void main(String[] args) {
		try {
			Scanner prefScan = new Scanner(prefs);
			ArrayList<GUIStatistic> formatList = new ArrayList<GUIStatistic>();
			String ln;
			while (prefScan.hasNextLine()) {
				ln = prefScan.nextLine();
				switch(ln.substring(1)) {
					case ("PointsFormat"): {
						if (prefScan.hasNextLine()) {
							ln = prefScan.nextLine();
							while(ln.charAt(0)!='-') {
								Scanner lnscan = new Scanner(ln);
								lnscan.useDelimiter("\\");
								formatList.add(new GUIStatistic(lnscan.next(),lnscan.nextInt(),lnscan.nextInt()));
							}
						}
						else {
							failAndError(-3);
						}
					}
					case ("GeneralFormat"): {
						System.out.println("Feature not support YET");
					}
					case ("PenaltiesFormat"): {
						System.out.println("Feature not support YET");
					}
					case ("Year"): {
						if (prefScan.hasNextLine()) {
							ln = prefScan.nextLine();
							if (ln.charAt(0)!= '-') {
								year = Integer.parseInt(ln);
							}
							else {
								failAndError(-4);
							}
						}
						else {
							failAndError(-4);
						}
					}
				}
			}
			try {
				tdb = new TeamDataBase(year, new Format(new ArrayList<Statistic>(){{for(GUIStatistic gs:formatList) add(gs.makeStat());}}));
			} catch (InvalidFormatException ife) {
				failAndError(-5);
			}
			
		} catch (FileNotFoundException e) {
			try {
				prefs.createNewFile();
				failAndError(-1);
				
			} catch (IOException e1) {
				failAndError(-2);
			}
		}
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new GUI("Scouting_Utility_Interface");
			}
		});
	}
	
	private static void failAndError(int stat) {
		GUI gui = new GUI("FAIL!!!");
		JOptionPane.showMessageDialog(gui, "Error: "+stat, "Setup Failed!", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}
