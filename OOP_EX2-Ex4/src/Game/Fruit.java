package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Geom.Point3D;
/**
 * 
 * @author Gil&Amit
 * this is the fruit class
 *
 */
public class Fruit {
	public int ID; //ID for the fruit
	public Point3D fruit; //fruit coordinates
	public Point3D fruitPixels; //fruit coordinates in pixels
	public BufferedImage fruitIMG;
	/**
	 * fruit constructor
	 * @param ID its ID
	 * @param Lat its latitude
	 * @param Lon its longitude
	 * @param Alt its altitude
	 */
	public Fruit(int ID, double Lat ,double Lon,double Alt) {
		this.ID =ID;
		this.fruit = new Point3D(Lat,Lon,Alt);
		this.fruitPixels=Map.LatLon2Pixels(Lon,Lat);
		try {
			fruitIMG = ImageIO.read(new File("fruit.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	/**
	 * another constructor form for fruit
	 * @param ID its ID
	 * @param fru coordinates for the fruit
	 */
	public Fruit(int ID, Point3D fru) {
		this.ID = ID;
		fruit = fru;
		this.fruitPixels = Map.LatLon2Pixels(fru.y(),fru.x());
		try {
			fruitIMG = ImageIO.read(new File("fruit.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public BufferedImage getFruitIMG() {
		return fruitIMG;
	}
	/**
	 * getting the fruit pixels in x
	 * @return x pixels
	 */
	public int getX() {
	    return (int)fruitPixels.x();
	}
	/**
	 * getting the fruit pixels in y
	 * @return y pixels
	 */
	public int getY() {
	    return (int)fruitPixels.y();
	}
	/**
	 * getting the fruit ID
	 * @return ID
	 */
	public int getID() {
		return ID;
	}

	public Point3D getFruit() {
		return fruit;
	}
}
