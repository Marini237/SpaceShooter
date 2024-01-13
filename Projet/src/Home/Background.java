package Home;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Background {
	private float x, y;
	private int vy;
	private Image image;

	public Background(int x, int y, int vy) throws SlickException {
			this.x = x;
			this.y = y;
			this.vy = vy;
		image = new Image("images/background.jpg");
	}

	public void dessiner(Graphics g) {
		g.drawImage(image,x, y);
	
	}
	public void deplacer(int delta) {
		float distance = vy * delta/1000f;
		y = y + distance;
	}
	public void redessiner(Graphics g) {
		float v=0;
			if(y!=0) {
			 v=y;
			 x=0;
			 g.drawImage(image,x, v-image.getHeight());	
			}
	}
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}
	

	public Image getImage() {
		return image;
	}

	public void setY(float y) {
		this.y = y;
	}

	public boolean horsEcran() {
		if(y<=0)
			return true;
		else
			return false;
	}
	
}
