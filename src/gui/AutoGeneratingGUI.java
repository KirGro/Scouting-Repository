package gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JOptionPane;

import team.*;
import team.teamException.*;

public class AutoGeneratingGUI {
	static ArrayList<GUIStatistic> pointsFormatList = new ArrayList<GUIStatistic>(), generalFormatList = new ArrayList<GUIStatistic>(), penaltiesFormatList = new ArrayList<GUIStatistic>();
	static TeamDataBase tdb;
	static final File saves = new File("data//serialedSave.data"), prefs = new File("preferences.txt"); 
	
	private static int year = Calendar.getInstance().get(Calendar.YEAR);
	private static GUI gui;
	private static boolean restoring = false;
	
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
								String name = lnscan.next(), defall = "0";
								int autoTele = lnscan.nextInt(), pos = lnscan.nextInt();
								if (lnscan.hasNext()) {defall = lnscan.next();}
								else {pointsFormatList.add(new GUIStatistic(name, autoTele, pos, defall));}
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
						System.out.println("Found general format start");
						if (prefScan.hasNextLine()) {
							System.out.println("Starting Read");
							ln = prefScan.nextLine();
							while(ln.length()>0&&ln.charAt(0)!='-') {
								Scanner lnscan = new Scanner(ln);
								lnscan.useDelimiter("/");
								String name = lnscan.next(), defall = "0";
								int autoTele = lnscan.nextInt(), pos = lnscan.nextInt();
								if (lnscan.hasNext()) {defall = lnscan.next();}
								else {generalFormatList.add(new GUIStatistic(name, autoTele, pos, defall));}
								if(prefScan.hasNextLine()) ln = prefScan.nextLine();
								else ln = "";
							}
							System.out.println("Read Line");
						}
						else {
							failAndError(-3);
						}
					} break;
					case ("PenaltiesFormat"): {
						System.out.println("Found penalties format start");
						if (prefScan.hasNextLine()) {
							System.out.println("Starting Read");
							ln = prefScan.nextLine();
							while(ln.length()>0&&ln.charAt(0)!='-') {
								Scanner lnscan = new Scanner(ln);
								lnscan.useDelimiter("/");
								String name = lnscan.next(), defall = "0";
								int autoTele = lnscan.nextInt(), pos = lnscan.nextInt();
								if (lnscan.hasNext()) {defall = lnscan.next();}
								else {penaltiesFormatList.add(new GUIStatistic(name, autoTele, pos, defall));}
								if(prefScan.hasNextLine()) ln = prefScan.nextLine();
								else ln = "";
							}
							System.out.println("Read Line");
						}
						else {
							failAndError(-3);
						}
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
					tdb = new TeamDataBase(year, new Format(new ArrayList<Statistic>(){{for(GUIStatistic gs:pointsFormatList) add(gs.makeStat());}}));
					if (generalFormatList.size()!=0) StaticData.setGeneralFormat(new Format(new ArrayList<Statistic>(){{for(GUIStatistic gs:generalFormatList)add(gs.makeStat());}})); 
					if (penaltiesFormatList.size()!=0) StaticData.setPenaltiesFormat(new Format(new ArrayList<Statistic>(){{for(GUIStatistic gs:penaltiesFormatList)add(gs.makeStat());}})); 
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
