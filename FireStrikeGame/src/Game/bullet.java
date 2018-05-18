package Game;

import javax.swing.JLabel;

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
