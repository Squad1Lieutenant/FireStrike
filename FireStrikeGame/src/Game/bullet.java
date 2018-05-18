package Game;

import javax.swing.JLabel;

//This interface should be implemented by every class that represents a
//type of Alien; each of those classes will need to implement all of the
//methods below; the benefit of having all of the Alien classes under
//this Alien interface is that they all can be grouped within a single
//List (e.g., ArrayList) and referenced via that list, instead of
//needing to be put into separate lists, requiring additional code;
//using this interface also allows others to know what methods they can
//expect to find in each of the Alien classes
public interface bullet
{
	public String getType();

	public void moveBullet();

	public JLabel getBulletImage();

	public int getWidth();
	public int getHeight();

	public int getX();
	public int getY();
	
	public int setBulletDamage();
}
