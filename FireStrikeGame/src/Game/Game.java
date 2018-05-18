package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Game implements ActionListener, KeyListener {
	
	private static final int WINDOW_WIDTH = 640;
	private static final int WINDOW_HEIGHT = 480;
	private static final int PLAY_AREA_WIDTH = 640; // For Later Use
	private static final int PLAY_AREA_HEIGHT = 450; // For Later Use
	
	private static JFrame gameFrame;
	private static JMenu gameMenuFile;
	private static JMenu gameMenuEdit;
	private static JMenu gameMenuAbout;
	private static JMenuBar gameMenuBar;
	private static JMenuItem gameMenuItemExit;
	private static JMenuItem gameMenuItemHelp;
	private static JMenuItem gameMenuItemSettings;

	
	private static boolean alwaysOnTop = false;

	public static void main(String[] Args) {
		//new Game();
		game();
	}
	
	public static void game() {
		
		//Create some JObjects
		gameFrame = new JFrame("FireStrike v0.1 preAlpha");
		gameMenuBar = new JMenuBar();
		gameMenuFile = new JMenu();
		gameMenuEdit = new JMenu();
		gameMenuAbout = new JMenu();
		gameMenuItemExit = new JMenuItem();
		gameMenuItemSettings = new JMenuItem();
		gameMenuItemHelp = new JMenuItem();
		
		//Set JFrame properties
		gameFrame.setAlwaysOnTop(alwaysOnTop);
		gameFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameFrame.setBackground(Color.RED);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);
		
		
		//Set menu bar object properties
		gameFrame.add(gameMenuBar);
		gameMenuBar.add(gameMenuFile);
		gameMenuFile.setText("File");
		gameMenuFile.add(gameMenuItemExit);
		gameMenuItemExit.setText("Exit");
		gameMenuBar.add(gameMenuEdit);
		gameMenuEdit.setText("Edit");
		gameMenuEdit.add(gameMenuItemSettings);
		gameMenuItemSettings.setText("Settings");
		gameMenuBar.add(gameMenuAbout);
		gameMenuAbout.setText("About");
		gameMenuAbout.add(gameMenuItemHelp);
		gameMenuItemHelp.setText("Help");
		//gameMenuBar.setSize(WINDOW_WIDTH, 30);
		
		//Add action Listener
		gameMenuItemExit.addActionListener(new Game());
		gameMenuItemExit.setActionCommand("exit");
		
		//Set JObjects visible
		gameMenuItemExit.setVisible(true);
		gameMenuItemHelp.setVisible(true);
		gameMenuItemSettings.setVisible(true);
		gameMenuFile.setVisible(true);
		gameMenuEdit.setVisible(true);
		gameMenuAbout.setVisible(true);
		gameMenuBar.setVisible(true);
		gameFrame.setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("exit")) 
		{
			gameFrame.dispose();
			System.exit(0);
		}
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



}
