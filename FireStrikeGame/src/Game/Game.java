package GameTrue;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

//import Game.Input;

public class Game implements ActionListener, KeyListener
{
	private static final int TIMER_DELAY = 0;
	private static JFrame gameFrame;
	private static JButton playGame, exit, enterIPButton;
	private static JLabel mainMenuLabel, enterIPLabel;
	private static String ip = "0.0.0.0";
	private static JTextField enterIP;
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;

	private static ImageIcon imgTrooper = new ImageIcon(Game.class.getResource("TrooperInGame.png"));
	private static JLabel lblTrooper = new JLabel(imgTrooper);
	private static int trooperX = 400, trooperY = 400, timerspeed = 5;
	private static KeyEvent event;
	private static ActionListener event1;

	public static void main(String[] Args) throws Exception
	{
		new Game();
	}

	public Game()
	{
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

		//Timer timer = new Timer(5, this);
		//timer.start();
	}

	public static void playGame() throws Exception
	{
		enterIP.setVisible(false);
		enterIPLabel.setVisible(false);
		enterIPButton.setVisible(false);
		lblTrooper.setVisible(true);
	}

	public static void enterIP()
	{
		enterIP.setVisible(true);
		enterIPLabel.setVisible(true);
		enterIPButton.setVisible(true);
	}

	public static void connect() throws Exception
	{

		socket = new Socket("10.70.4.117", 7777);

		System.out.println("Connection Established");

		in = new DataInputStream(socket.getInputStream());

		out = new DataOutputStream(socket.getOutputStream());

		Input input = new Input(in);

		Thread thread = new Thread(input);

		thread.start();

		playGame();

	}

	public static void game() throws Exception
	{
		enterIP.setVisible(false);
		enterIPLabel.setVisible(false);
		enterIPButton.setVisible(false);
		lblTrooper.setVisible(true);
	}

	public void actionPerformed(ActionEvent event)
	{
		if (event.getActionCommand().equals("exit"))
		{
			System.exit(0);
		}
		if (event.getActionCommand().equals("playgame"))
		{
			playGame.setVisible(false);
			exit.setVisible(false);
			mainMenuLabel.setVisible(false);
			enterIP();
		}

		if (event.getActionCommand().equals("enterip"))
		{
			ip = enterIPButton.getText();
			try
			{
				connect();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			try
			{
				playGame();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	public void actionPerformed1(ActionEvent event1)
	{
		System.out.println("Test");
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_UP)
		{
			//trooperY -= 10;
			try
			{
				out.writeInt(lblTrooper.getY());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			try
			{
				lblTrooper.setLocation(trooperX, in.readInt());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN)
		{
			trooperY += 10;
			lblTrooper.setLocation(trooperX, trooperY);
		}
		if (event.getKeyCode() == KeyEvent.VK_LEFT)
		{
			trooperX -= 10;
			lblTrooper.setLocation(trooperX, trooperY);
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			trooperX += 10;
			lblTrooper.setLocation(trooperX, trooperY);
		}
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent event)
	{
		// TODO Auto-generated method stub

	}
}

class Input implements Runnable
{

	DataInputStream in;

	public Input(DataInputStream in)
	{

		this.in = in;

	}

	public void run()
	{

		while (true)
		{

			String message;

			try
			{

				message = in.readUTF();

				System.out.println(message);

			}
			catch (IOException e)
			{

				e.printStackTrace();

			}

		}

	}

}
