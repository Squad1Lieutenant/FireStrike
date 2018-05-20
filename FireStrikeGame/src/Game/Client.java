package Game;

//Import Statements to fetch all required libraries.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.util.*;
import java.io.*;

public class Client implements ActionListener, KeyListener {

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
	private static JMenuItem menuAlwaysOnTop;
	private static JLabel lblText;
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;

	private static boolean alwaysOnTop = false;

	public static void main(String[] Args) throws Exception {
		game();
	}

	public static void game() throws Exception {
		// Create some JObjects

		gameFrame = new JFrame("FireStrike v0.1 preAlpha");
		gameMenuBar = new JMenuBar();
		gameMenuFile = new JMenu();
		gameMenuEdit = new JMenu();
		gameMenuAbout = new JMenu();
		gameMenuItemExit = new JMenuItem();
		gameMenuItemSettings = new JMenuItem();
		gameMenuItemHelp = new JMenuItem();
		lblText = new JLabel();

		// Set JFrame properties
		gameFrame.setLayout(null);
		gameFrame.setAlwaysOnTop(alwaysOnTop);
		gameFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		gameFrame.setBackground(Color.RED);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setResizable(false);

		// Set menu bar object properties
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
		gameMenuBar.setSize(WINDOW_WIDTH, 30);
		// gameMenuItemSettings.add(menuAlwaysOnTop);
		// menuAlwaysOnTop.setText("Always On Top");
		gameMenuItemExit.setActionCommand("exit");
		gameFrame.add(lblText);
		lblText.setText("...");
		lblText.setLocation(0, 320);
		lblText.setOpaque(true);
		lblText.setSize(PLAY_AREA_WIDTH, 25);

		// Set JObjects visible
		gameMenuItemExit.setVisible(true);
		gameMenuItemHelp.setVisible(true);
		gameMenuItemSettings.setVisible(true);
		gameMenuFile.setVisible(true);
		gameMenuEdit.setVisible(true);
		gameMenuAbout.setVisible(true);
		gameMenuBar.setVisible(true);
		gameFrame.setVisible(true);

		System.out.println("Connecting...");
		socket = new Socket("73.15.227.253", 7777);
		System.out.println("Connection Successful");
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
		Input input = new Input(in);
		Thread thread = new Thread(input);
		thread.start();
		Scanner sc = new Scanner(System.in);
		while(true) {
			String sendMessage = sc.nextLine();
			out.writeUTF(sendMessage);
			
		}
		

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

class Input implements Runnable {
	
	DataInputStream in;
	public Input(DataInputStream in) {
		this.in = in;
	}
	
	public void run() {
		while(true) {
		String message;
		try {
		message = in.readUTF();
		System.out.println(message);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
}