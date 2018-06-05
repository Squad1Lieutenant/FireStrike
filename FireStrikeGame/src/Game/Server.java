package Game;

import java.io.*;

import java.net.*;
import java.util.Scanner;

public class Server

{

	private static final int MAX_USERS = 10;

	static Scanner sc;

	static ServerSocket serverSocket;

	static Socket socket;

	static DataOutputStream out;

	static DataInputStream in;

	static Users[] user = new Users[MAX_USERS];

	public static void main(String[] args) throws Exception

	{

		System.out.println("Starting Server...");

		serverSocket = new ServerSocket(8888);

		System.out.println("Server Started...");

		while (true)

		{

			socket = serverSocket.accept();

			for (int i = 0; i < 10; i++)

			{

				System.out.println("Connection From:" + socket.getInetAddress());

				out = new DataOutputStream(socket.getOutputStream());

				in = new DataInputStream(socket.getInputStream());

				if (user[i] == null)
				{

					user[i] = new Users(out, in, user);

					Thread thread = new Thread(user[i]);

					thread.start();

					break;
				}
			}

		}

	}

}

class Users implements Runnable

{

	DataOutputStream out;

	DataInputStream in;

	Users[] user = new Users[10];
	
	String name;

	public Users(DataOutputStream out, DataInputStream in, Users[] user)
	{
		this.out = out;
		this.in = in;
		this.user = user;
	}

	public void run()
	{
		try
		{
			name = in.readUTF();
		}
		catch (IOException e1)
		{
		e1.printStackTrace();
		}
		
		while (true)

		{

			int commandW;
			int commandS;
			String commandHealth;
			String commandShoot;

			try

			{
				commandW = in.readInt() - 5;
				commandS = in.readInt() + 5;
				commandShoot = in.readUTF();
				commandHealth = in.readUTF();
							
				for (int i = 0; i < 10; i++)

				{

					if (user[i] != null)

					{
						
						user[i].out.writeInt(commandW);
						user[i].out.writeInt(commandS);
						//user[i].out.writeUTF(str);
						//user[i].out.writeUTF(str);
					}

				}

			}

			catch (IOException e)

			{


				this.out = null;
				this.in = null;

			}

		}

	}

}
