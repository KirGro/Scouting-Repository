package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

import team.*;
import team.teamException.*;

public class AutoGeneratingGUI {
	private static int year = Calendar.getInstance().get(Calendar.YEAR);
	static TeamDataBase tdb;
	private static GUI gui;
	private final static File prefs = new File("preferences.txt");
	static final File saves = new File("data//serialedSave.data"); 
	private static ArrayList<GUIStatistic> formatList = new ArrayList<GUIStatistic>();
	public static boolean restoring = false;
	
	public static void main(String[] args) {
		readDataPrefs();
		System.out.println("SuccessFully Read It!");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new GUI("Scouting_Utility_Interface");
				System.out.println("GUI made It!");
			}
		});
	}
	
	private static void readDataPrefs() {
		try {
			Scanner prefScan = new Scanner(prefs);
			String ln;
			while (prefScan.hasNextLine()) {
				ln = prefScan.nextLine();
				switch(ln.substring(1)) {
					case ("PointsFormat"): {
						System.out.println("Found Points format start");
						if (prefScan.hasNextLine()) {
							System.out.println("Starting Read");
							ln = prefScan.nextLine();
							while(ln.length()>0&&ln.charAt(0)!='-') {
								Scanner lnscan = new Scanner(ln);
								lnscan.useDelimiter("/");
								formatList.add(new GUIStatistic(lnscan.next(),lnscan.nextInt(),lnscan.nextInt()));
								if(prefScan.hasNextLine()) ln = prefScan.nextLine();
								else ln = "";
							}
							System.out.println("Read Line");
						}
						else {
							failAndError(-3);
						}
					} break;
					case ("GeneralFormat"): {
						System.out.println("Feature not support YET");
					} break;
					case ("PenaltiesFormat"): {
						System.out.println("Feature not support YET");
					} break;
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
					} break;
					case ("Restore"): {
						if (prefScan.hasNextLine()) {
							ln = prefScan.nextLine();
							if (ln.charAt(0)!= '-') {
								restoring = Boolean.parseBoolean(ln);
							}
							else {
								failAndError(-5);
							}
						}
						else {
							failAndError(-5);
						}
					} break;
				}
			}
			if(restoring) {
				ObjectInputStream inStream;
				try {
					inStream = new ObjectInputStream(new FileInputStream(AutoGeneratingGUI.saves));
					tdb = (TeamDataBase) inStream.readObject();
					inStream.close();
					System.out.println("Read tdb");
				} catch (IOException e) {
					failAndError(-6);
				} catch (ClassNotFoundException e) {
					failAndError(-6);
				}
				
			}
			else {
				try {
					tdb = new TeamDataBase(year, new Format(new ArrayList<Statistic>(){{for(GUIStatistic gs:formatList) add(gs.makeStat());}}));
				} catch (InvalidFormatException ife) {
					failAndError(-7);
				}
			}
			
		} catch (FileNotFoundException e) {
			try {
				prefs.createNewFile();
				failAndError(-1);
				
			} catch (IOException e1) {
				failAndError(-2);
			}
		}
		
	}
	
	private static void failAndError(int stat) {
		GUI gui = new GUI("FAIL!!!");
		JOptionPane.showMessageDialog(gui, "Error: "+stat+"\nUse commandline to see a detailed problem description", "Setup Failed!", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}
