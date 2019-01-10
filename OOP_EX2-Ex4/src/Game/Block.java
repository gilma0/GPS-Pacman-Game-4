package Game;

import Geom.Point3D;
/**
 * this is the block class
 * @author Gil & Amit
 *
 */
public class Block {
	public int ID; //ID for the block
	public Point3D blockA; //block coordinates
	public Point3D blockB;
	public Point3D blockPixelsA; //block coordinates in pixels
	public Point3D blockPixelsB;
	
	public Block(int ID, double LatA ,double LonA,double AltA, double LatB ,double LonB,double AltB) {
		this.blockA = new Point3D(LatA,LonA,AltA);
		this.blockB = new Point3D(LatB,LonB,AltB);
		this.blockPixelsA=Map.LatLon2Pixels(LonA,LatA);
		this.blockPixelsB=Map.LatLon2Pixels(LonB,LatB);
		this.ID = ID;
	}

	public int getID() {
		return ID;
	}

	public Point3D getBlockA() {
		return blockA;
	}

	public Point3D getBlockB() {
		return blockB;
	}

	public Point3D getBlockPixelsA() {
		return blockPixelsA;
	}

	public Point3D getBlockPixelsB() {
		return blockPixelsB;
	}
	/**
	 * getting the fruit pixels in x
	 * @return x pixels
	 */
	public int getXA() {
	    return (int)blockPixelsA.x();
	}
	/**
	 * getting the fruit pixels in y
	 * @return y pixels
	 */
	public int getYA() {
	    return (int)blockPixelsA.y();
	}
	/**
	 * getting the fruit pixels in x
	 * @return x pixels
	 */
	public int getXB() {
	    return (int)blockPixelsB.x();
	}
	/**
	 * getting the fruit pixels in y
	 * @return y pixels
	 */
	public int getYB() {
	    return (int)blockPixelsB.y();
	}
}


