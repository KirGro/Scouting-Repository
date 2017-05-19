package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener, WindowListener, WindowFocusListener , WindowStateListener{
	
	private static GridBagConstraints c = new GridBagConstraints();
	
	private static String preferencesDoc = "Preferences.txt", dataDoc = "", bigDataDoc = "", bigDataR2Doc = "", condesedName = "";
	private static String[] preferences = new String[4];
	private static Object[] generalInfoInput, generalInfoReview;
	private static Component[] pointsInput, penaltiesInput, endInput, pointsReview, penaltiesReview, endReview;
	
	private static JPanel cards = new JPanel(new CardLayout());
	private static JPanel input = new JPanel(new GridBagLayout()), review = new JPanel(new GridBagLayout());
	private static JPanel pointsIPanel = new JPanel(new GridBagLayout()), penaltiesIPanel = new JPanel(new GridBagLayout()), generalIPanel = new JPanel(new GridBagLayout()), endIPanel = new JPanel(new GridBagLayout());
	private static JPanel pointsRPanel = new JPanel(new GridBagLayout()), penaltiesRPanel = new JPanel(new GridBagLayout()), generalRPanel = new JPanel(new GridBagLayout()), endRPanel = new JPanel(new GridBagLayout());
	
	
	
	public GUI(String s) {
		super(s);
		if(!s.equals("FAIL!!!")) createAndShowGUI();
	}
	
	
	private void createAndShowGUI() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		addWindowFocusListener(this);
		addWindowStateListener(this);
	    addComponentToPane(getContentPane());
	    //gui.setJMenuBar(menuBar);
	    
		setSize(430, 400);
		setResizable(true);
		setVisible(true);	
	}
	
	
	
	private void addComponentToPane(Container con) {
		
	}
	
	
	
	@Override
	public void windowStateChanged(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowGainedFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowLostFocus(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(this, "Press Okay to exit and save", "Exiting", JOptionPane.PLAIN_MESSAGE);
		try {
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(AutoGeneratingGUI.saves));
			outStream.writeObject(AutoGeneratingGUI.tdb);
			outStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
