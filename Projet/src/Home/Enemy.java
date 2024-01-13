package Home;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Enemy {
    private final int HEALTH = 10;
    private final int DAMAGE = 10;
    
    private int health;
    private int damage;
    private double x;
    private double y;
    private Image enemy;
    private Shape enemyHB; 
    
    public Enemy(String path) throws SlickException {
        health = 20;
        damage = 10;
        x = 0.0;
        y = 0.0;
        enemy = new Image(path);
        enemyHB = new Ellipse((int)x, (int)y, 32, 32);
    }
    
    public Shape getHB() {
        return enemyHB;
    }
    
    public void setHB(Shape hitbox) {
        enemyHB = hitbox;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void setHealth(int newHP) {
        health = newHP;//changed for setting health normal
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void setDamage(int damage) {
        this.damage = damage;
    }
    
    public Image getImage() {
        return enemy;
    }
    
    public double getY() {
        return y;
    }
    
    public double getX() { 
        return x;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public void setX(double x) {
        this.x = x;
    }
}
