package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;
/**
 * 
 * @author Gil&Amit
 * this is the player class
 *
 */
public class Player {
	//player attributes
	public int ID;
	public Point3D player;
	public double Speed; //the speed the player moves
	public double Radius; //the player eating radius
	public Path path; //the path of the fruits this player has eaten
	public Point3D playerPixels; //the player place changed into pixels
	public BufferedImage playerIMG;

	/**
	 * setting this player pixels
	 * @param playerPixels the pixels given
	 */
	public void setPackmanPixels(Point3D playerPixels) {
		this.playerPixels = playerPixels;
	}
	/**
	 * getting X pixels
	 * @return X pixels
	 */
	public int getX() {
	    return (int) playerPixels.x();
	}
	/**
	 * getting Y pixels
	 * @return Y pixels
	 */
	public int getY() {
	    return (int) playerPixels.y();
	}
	
	/**
	 * this is a player constructor
	 * @param ID given ID
	 * @param Lat given latitude
	 * @param Lon given longitude
	 * @param Alt given altitude
	 * @param Speed given speed
	 * @param Radius given eat radius
	 */
	public Player(int ID, double Lat ,double Lon,double Alt ,double Speed, double Radius) {
		this.ID =ID;
		player = new Point3D(Lat,Lon,Alt);
		this.Speed=Speed;
		this.Radius=Radius;
		this.path = new Path();
		this.path.add(new Fruit(999, Lat, Lon, Alt));
		this.playerPixels=Map.LatLon2Pixels(Lon,Lat);
		try {
			this.playerIMG = ImageIO.read(new File("Pacman_red.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * another form of packman constructor
	 * @param ID given ID
	 * @param pack given Place
	 */
	

	
	public Player(int ID, Point3D pack) {
		this.ID = ID;
		this.Speed = 1;
		this.Radius = 1;
		player = pack;
		this.playerPixels = Map.LatLon2Pixels(pack.y(),pack.x());
		this.path = new Path();
		this.path.add(new Fruit(999, pack));
		try {
			this.playerIMG = ImageIO.read(new File("Pacman_red.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public BufferedImage getPlayerIMG() {
		return playerIMG;
	}

	/**
	 * getting this player path
	 * @return path
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * getting this player ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}
	/**
	 * getter for this player coordinates
	 * @return coordinates object
	 */
	public Point3D getPlayer() {
		return player;
	}
	/**
	 * getting this player speed
	 * @return speed
	 */
	public double getSpeed() {
		return Speed;
	}
	/**
	 * getting this player eating radius
	 * @return radius
	 */
	public double getRadius() {
		return Radius;
	}
	/**
	 * setting this player coordinates
	 * @param player given coordinates
	 */
	public void setPlayer(Point3D packman) {
		this.player = packman;
	}

	
	

	
}
