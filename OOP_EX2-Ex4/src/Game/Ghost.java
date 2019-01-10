package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;
/**
 * this is the ghost class
 * @author Gil & Amit
 * 
 */
public class Ghost {
	public int ID;
	public Point3D ghost;
	public double Speed; //the speed the ghost moves
	public double Radius; //the ghost eating radius
	public Point3D ghostPixels; //the ghost place changed into pixels
	public BufferedImage ghostIMG;
	
	public BufferedImage getGhostIMG() {
		return ghostIMG;
	}
	public Point3D getGhost() {
		return ghost;
	}
	public void setGhost(Point3D ghost) {
		this.ghost = ghost;
	}
	public Point3D getGhostPixels() {
		return ghostPixels;
	}
	public void setGhostPixels(Point3D ghostPixels) {
		this.ghostPixels = ghostPixels;
	}
	public int getID() {
		return ID;
	}
	public double getSpeed() {
		return Speed;
	}
	public double getRadius() {
		return Radius;
	}
	/**
	 * getting X pixels
	 * @return X pixels
	 */
	public int getX() {
	    return (int) ghostPixels.x();
	}
	/**
	 * getting Y pixels
	 * @return Y pixels
	 */
	public int getY() {
	    return (int) ghostPixels.y();
	}

	
	public Ghost(int ID, double Lat ,double Lon,double Alt ,double Speed, double Radius) {
		this.ID =ID;
		this.ghost = new Point3D(Lat,Lon,Alt);
		this.Speed=Speed;
		this.Radius=Radius;
		this.ghostPixels=Map.LatLon2Pixels(Lon,Lat);
		try {
			ghostIMG = ImageIO.read(new File("pacman_ghost.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
}
