package Home;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class ShotPlane {
	private double x, y;
	private Image shotp;
	private Shape shotpHB;
	public ShotPlane(String path) throws SlickException {
		shotp = new Image(path);
        x = 0;
        y = 0;
        shotpHB = new Ellipse((int)x, (int)y, 7, 15);
	}
	public ShotPlane(ShotPlane copy) {
        x = copy.getX();
        y = copy.getY();
        shotp = copy.getImage();
        shotpHB = copy.getHB();
    }
	public Shape getHB() {
        return shotpHB;
    }
    
    public void setHB(Shape hitbox) {
        shotpHB = hitbox;
    }
    
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	public Image getImage() {
        return shotp;
    }

	
}
