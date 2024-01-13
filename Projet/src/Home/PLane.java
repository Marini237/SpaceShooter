package Home;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class PLane {
	 private double health;
	 private int damage;
	private double x,y;
	private double xV, yV;
	private Image plane;
	private Shape planeHB;
	public PLane(String path) throws SlickException {
		health = 100;
        damage = 10;
		x = 0;
		y = 0;
		xV = 1;
		yV = 1;  
		plane = new Image(path);
		planeHB = new Ellipse((int)x, (int)y, 88, 120);
	}
	
	public double getHealth() {
		return health;
	}
	public void setHealth(double health) {
		this.health = health;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public double getxV() {
		return xV;
	}
	public void setxV(double xV) {
		this.xV = xV;
	}
	public double getyV() {
		return yV;
	}
	public void setyV(double yV) {
		this.yV = yV;
	}
	public Image getPlane() {
		return plane;
	}
	public void setPlane(Image plane) {
		this.plane = plane;
	}
	public Shape getPlaneHB() {
		return planeHB;
	}
	public void setPlaneHB(Shape planeHB) {
		this.planeHB = planeHB;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Image getImage() {
		return plane;
	}
	
	

}
