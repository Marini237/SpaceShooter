package Home;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class ShotEnemy {
	private double x, y;
	private Image shote;
	private Shape shoteHB;
	public ShotEnemy(String path) throws SlickException {
		shote = new Image(path);
        x = 0;
        y = 0;
        shoteHB = new Ellipse((int)x, (int)y, 7, 15);
	}
	public ShotEnemy(ShotEnemy copy) {
        x = copy.getX();
        y = copy.getY();
        shote = copy.getImage();
        shoteHB = copy.getHB();
    }
	public Shape getHB() {
        return shoteHB;
    }
    
    public void setHB(Shape hitbox) {
        shoteHB = hitbox;
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
        return shote;
    }

	

}
