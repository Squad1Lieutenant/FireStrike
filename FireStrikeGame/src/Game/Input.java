package Game;

import java.io.*;

class Input implements Runnable {

	DataInputStream in;
	Game client;

	public Input(DataInputStream in, Game c) {

		this.in = in;
		this.client = c;
		

	}



	public void run() {
		while (true) {
			try {
				int playerid = in.readInt();
				int x = in.readInt();
				int y = in.readInt();		
				client.updateCoordinates(playerid, x, y);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}