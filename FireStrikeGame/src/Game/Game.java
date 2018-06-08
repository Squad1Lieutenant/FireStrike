package Game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Game implements Runnable, KeyListener, ActionListener {
	private static final int TIMER_SPEED = 5;
	private static JFrame gameFrame;
	private static JButton playGame, exit, enterIPButton;
	private static JLabel mainMenuLabel, enterIPLabel;
	private static String ip = "";
	private static JTextField enterIP;
	private static ImageIcon imgTrooper = new ImageIcon(Game.class.getResource("TrooperInGame.png"));
	private static JLabel lblTrooper = new JLabel(imgTrooper);
	private int trooperX = 400, trooperY = 400, timerspeed = 5, CommandW = 0;
	private static KeyEvent event;
	private static ActionListener event1;
	
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	static int playerid;

	int[] x = new int[4];
	int[] y = new int[4];

	boolean left, up, right, down;

	int playerx;
	int playery;

	public static void main(String [] args) {
		new Game();
		
	}
	public Game() {
		// The frame of FireStrike
				gameFrame = new JFrame();

				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setSize(1360, 768);
				gameFrame.setTitle("FireStrike");
				gameFrame.setLocationRelativeTo(null);
				gameFrame.setResizable(false);
				gameFrame.setFocusable(true);
				gameFrame.getContentPane().setBackground(new Color(0, 255, 135));
				gameFrame.setLayout(null);
				gameFrame.addKeyListener(this);
				

				// Create the character class "Trooper"
				lblTrooper = new JLabel(imgTrooper);
				lblTrooper.setSize(700, 600);
				lblTrooper.setLocation(trooperX, trooperY);
				lblTrooper.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
				gameFrame.add(lblTrooper);
				lblTrooper.setVisible(false);

				// The "Play Game" Button
				playGame = new JButton("Play Game");
				playGame.setSize(300, 100);
				playGame.setLocation(500, 400);
				playGame.setVisible(true);
				playGame.addActionListener(this);
				playGame.setActionCommand("playgame");
				gameFrame.add(playGame);

				// The "Exit" Button
				exit = new JButton("Exit");
				exit.setSize(300, 100);
				exit.setLocation(500, 550);
				exit.setVisible(true);
				exit.addActionListener(this);
				exit.setActionCommand("exit");
				gameFrame.add(exit);

				// Main Menu Label
				mainMenuLabel = new JLabel("FireStrike 0.1 Pre-Alpha");
				mainMenuLabel.setSize(500, 600);
				mainMenuLabel.setLocation(420, -100);
				mainMenuLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
				mainMenuLabel.setVisible(true);
				gameFrame.add(mainMenuLabel);

				// Enter IP Text Field
				enterIP = new JTextField(20);
				enterIP.setSize(500, 50);
				enterIP.setLocation(420, 400);
				enterIP.addKeyListener(this);
				gameFrame.add(enterIP);
				enterIP.setVisible(false);

				// Enter IP Label
				enterIPLabel = new JLabel("Enter IP in the text box below:");
				enterIPLabel.setSize(700, 600);
				enterIPLabel.setLocation(390, -100);
				enterIPLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
				gameFrame.add(enterIPLabel);
				enterIPLabel.setVisible(false);

				// Enter IP Button
				enterIPButton = new JButton("Enter IP");
				enterIPButton.setSize(300, 100);
				enterIPButton.setLocation(500, 550);
				enterIPButton.addActionListener(this);
				enterIPButton.setActionCommand("enterip");
				gameFrame.add(enterIPButton);
				enterIPButton.setVisible(false);

				gameFrame.setVisible(true);

				Timer timer = new Timer(TIMER_SPEED, this);
				timer.setActionCommand("timer");
				timer.start();
	}
	
	public static void getIP() 
	{
		mainMenuLabel.setVisible(false);
		exit.setVisible(false);
		playGame.setVisible(false);
		enterIPButton.setVisible(true);
		enterIP.setVisible(true);
		enterIPLabel.setVisible(true);
		ip = enterIPLabel.getText();
		
		
	}
	public void connect()
	{
		try {
			
			socket = new Socket("localhost", 7777);
			System.out.println("Connection Established");
			in = new DataInputStream(socket.getInputStream());
			playerid = in.readInt();
			out = new DataOutputStream(socket.getOutputStream());
			Input input = new Input(in, this);
			Thread thread = new Thread(input);
			thread.start();
			Thread thread2 = new Thread(this);
			thread2.start();
			
			

		} catch (Exception E) {
			System.out.println("Cannot Start Client Do To Errors");
		}
	}
	
	public void updateCoordinates(int pid, int x2, int y2) {
		this.x[pid] = x2;
		this.y[pid] = y2;

	}

	public void paint(Graphics g) {
		for (int i = 0; i < 4; i++) {
			g.drawOval(x[i], y[i], 10, 10);
		}
	}

	public void run() {
		while (true) {
			if (up == true) {
				playery -= 10;
			}
			if (down == true) {
				playery += 10;
			}
			if (left == true) {
				playery -= 10;
			}
			if (right == true) {
				playerx += 10;
			}
			if (up || down || left || right) {
				try {
					out.writeInt(playerid);
					out.writeInt(playerx);
					out.writeInt(playery);
				} catch (Exception e) {
					System.out.println("Error Sending Information");
				}
				gameFrame.repaint();
			}
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = true;
		}

		if (e.getKeyCode() == 38) {
			up = true;
		}

		if (e.getKeyCode() == 39) {
			right = true;
		}

		if (e.getKeyCode() == 40) {
			down = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 37) {
			left = false;
		}

		if (e.getKeyCode() == 38) {
			up = false;
		}

		if (e.getKeyCode() == 39) {
			right = false;
		}

		if (e.getKeyCode() == 40) {
			down = false;
		}
	}
	

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("exit")) 
		{
			gameFrame.dispose();
			System.exit(0);
		}
		
		if (e.getActionCommand().equals("playgame"))
		{
			getIP();
		}
		
		if (e.getActionCommand().equals("enterip"))
		{
			connect();
		}
	}
}

