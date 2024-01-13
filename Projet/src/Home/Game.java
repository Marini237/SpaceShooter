package Home;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Game extends BasicGameState {
	Background background_img;
	protected static ArrayList<Enemy> enemies;
	protected static ArrayList<ShotPlane> shots;
	protected static ArrayList<ShotEnemy> shotes;
	protected Image [] bgArray;
	protected static Input input;
	private float countX = 0, countY = 0;
	protected static PLane player;
	protected static Options options;
	protected int timePassed = 0;
	protected int shotDelay = 0;
	protected int shotDelaye = 0;
	protected Random rng = new Random();
	public static int level=0;
	public static int score = 0;
	public static int spawnTime = 0;
	static double missleSpeed = 2;
	static double missleSpeeds = 2;
	static double enemySpeed = 1;
	static int timedShotDelay = 400;
	static int timedShotDelaye = 400;
	static int finalScore;

	private Music music;

	protected Image playerRight, playerLeft, playerUp;
	protected Shape enemyHB;
	protected Shape planeHB;
	protected Shape shotpHB;
	protected Shape shoteHB;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		enemies = new ArrayList<Enemy>();
		shots = new  ArrayList<ShotPlane>();	
		shotes = new ArrayList<ShotEnemy>();
		player = new PLane("images/plane.png");
		music = new Music("images/music.ogg");
	     bgArray = new Image[3];
		bgArray[0] = new Image("images/background.jpg");
		bgArray[1] = new Image("images/background.jpg");
		bgArray[2] = new Image("images/background.jpg");

		planeHB = new Ellipse((int)player.getX(), (int)player.getY(), 88, 120);

		player.setX(806);
		player.setY(730);
		music.play();
		input=gc.getInput();
	}
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
		//États du menu pause du jeu
		if (gc.getInput().isKeyPressed(Input.KEY_P)) {
			sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());//3
		}
		//Détection et mouvement du contrôle du joueur
		int w= input.getMouseX();
		int h= input.getMouseY();
		int w2= player.getImage().getWidth();
		int h2= player.getImage().getHeight();
		player.setX(w-w2/2);
		player.setY(h -h2/2);
		
		//Remarque : i est un paramètre de la méthode. C'est un compteur de temps.
		shotDelay += i;
		if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {            
                    if (shotDelay > timedShotDelay) {
                        shotDelay = 0;

                        shootEnemy();
                    }
		}
		shotDelaye+=i;
		if (shotDelaye > timedShotDelaye) {
            shotDelaye = 0;

            shootPlane();
        }
		
		//Définit la hitbox du joueur.
		planeHB.setLocation((float)player.getX(), (float)player.getY());
		
		// Incrémente la minuterie, utilisée pour ajouter du temps entre les spawns ennemis
		timePassed += i;
		if (timePassed > spawnTime) {
			timePassed = 0;
			addEnemy();
		}
		
		// ArrayList contenant les navires ennemis
		for (Enemy e : enemies) {
			e.setY(e.getY() + enemySpeed);
		}
		
		// ArrayList contenant les missiles du joueur
		for (ShotPlane m : shots) {
			m.setY(m.getY() - missleSpeed);
		}
		for (ShotEnemy n : shotes) {
			n.setY(n.getY() + missleSpeeds);
		}
		
		// Tant qu'il y a des ennemis dans la ArrayList, les déposer lentement du haut de l'écran
     // Mettre à jour l'emplacement de l'ennemi et détecter les collisions entre hitbox
		for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
            Enemy curEnemy = enemyIterator.next();
            enemyHB.setLocation((float)curEnemy.getX(), (float)curEnemy.getY());

            if (enemyHB.intersects(planeHB)) 
            {
                score -= 200;

                player.setHealth(player.getHealth() - 20);
                if (player.getHealth() <= 0) 
                {
                        sbg.enterState(4);//4
                        finalScore = score;
                }

                enemyIterator.remove();
            }
		for (Iterator<ShotPlane> shotpIterator = shots.iterator(); shotpIterator.hasNext();) {
           ShotPlane curshotp = shotpIterator.next();
        // Définir la hitbox pour l'ennemi et le joueur
            shotpHB.setLocation((float)curshotp.getX(), (float)curshotp.getY());
     
       // Supprime le missile s'il quitte le haut de l'écran   
            if (curshotp.getY() < 0) {
                    shotpIterator.remove();
            }   
           
      // Le missile du joueur touche l'ennemi, supprime les deux objets
            if (shotpHB.intersects(enemyHB)) {
                    enemyIterator.remove();
                    shotpIterator.remove();
                    score += 100;
            }
        }
     }
		for (Iterator<ShotEnemy> shotpIterator = shotes.iterator(); shotpIterator.hasNext();) {
	           ShotEnemy curshote = shotpIterator.next();
	        // Définir la hitbox pour l'ennemi et le joueur
	            shoteHB.setLocation((float)curshote.getX(), (float)curshote.getY());
	     
	            if (curshote.getY() > 850) {
	                    shotpIterator.remove();
	            }   
	           
	      // Le missile de l énémies touche le joueurs, supprime les deux objets
	            if (shoteHB.intersects(planeHB)) 
	            {
	                score -= 10;

	                player.setHealth(player.getHealth() - 1);
	                shotpIterator.remove();
	                if (player.getHealth() <= 0) 
	                {
	                        sbg.enterState(4);//4
	                        finalScore = score;
	                }
	                
	        }
	            for (Iterator<ShotPlane> shotpIterators = shots.iterator(); shotpIterators.hasNext();) {
	                ShotPlane curshotp = shotpIterators.next();
	             // Définir la hitbox pour l'ennemi et le joueur
	                 shotpHB.setLocation((float)curshotp.getX(), (float)curshotp.getY());
	          
	            // Supprime le missile s'il quitte le haut de l'écran   
	                 if (curshotp.getY() < 0) {
	                         shotpIterators.remove();
	                 }
	                 if(shotpHB.intersects(shoteHB)) {
	                	 shotpIterators.remove();
	                	 shotpIterator.remove();
	                 }
		}
		}
		
		// Supprimer les ennemis s'ils touchent le fond
		for (int count = enemies.size() - 1; count >= 0; count--) {
            Enemy e = enemies.get(count);
            if (e.getY() > 850 || e.getHealth() < 0) {
                    enemies.remove(count);
            }
		}
		
// Vérifier l'image défilante en arrière-plan
// C'est pour le défilement infini en arrière-plan
		if (countX < 1700) {
            countX += .2;
}
else {
            countX = -1700;
}

if (countY < 850) {
            countY += .2;
}
else {
            countY = -850;
}
}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		int bgCounter = -1;
		for (Image bg : bgArray) {
                    g.drawImage(bg, 0, bgCounter * 850 + countY);
                    bgCounter++;
		}
		
		player.getImage().draw((int)player.getX(), (int)player.getY());
		
		for (ShotPlane s : shots) {
            s.getImage().draw((int)s.getX(), (int)s.getY());
           } 
		for (ShotEnemy r : shotes) {
            r.getImage().draw((int)r.getX(), (int)r.getY());
           } 
		
		for (Enemy e: enemies) {
            e.getImage().draw((int)e.getX(), (int)e.getY());
           }
		g.drawString("Score: " + score, 30, 50);
		g.drawString("Health: " + (int)player.getHealth(), 30, 70);
		g.drawString("level: " + level,30, 30);
	}	
		public void addEnemy() throws SlickException {
            Enemy e = new Enemy("images/Enemy SpaceShip Blue.png");
            e.setX(rng.nextInt(1650));
            enemyHB = new Ellipse((int)e.getX() + 50, (int)e.getY(), 32, 32);
            enemies.add(e);
	}
		public void shootEnemy() throws SlickException {
            ShotPlane m = new ShotPlane("images/missile.png");
            m.setX(player.getX()+(player.getImage().getWidth()/2)-(m.getImage().getWidth()/2));
            m.setY(player.getY()+m.getImage().getHeight());
            shotpHB = new Ellipse((int)m.getX(), (int)m.getY(), 7, 15);;
            shots.add(m);
		}
		public void shootPlane() throws SlickException {
            ShotEnemy n = new ShotEnemy("images/shot.png");
            for(Enemy e:enemies) {
            	 n.setX(e.getX()+(e.getImage().getWidth()/2)-(n.getImage().getWidth()/2));
                 n.setY(e.getY()+n.getImage().getHeight());
                 shoteHB = new Ellipse((int)n.getX(), (int)n.getY(), 2, 10);;
                 shotes.add(n);
            }
           
		}
		public int getID() {
            return 1;
	  }
		public static void updatePlay(){
            player.setX(806);
            player.setY(730);
            enemies = new ArrayList<Enemy>();
            shots = new  ArrayList<ShotPlane>();
            shotes= new ArrayList<ShotEnemy>();
            player.setHealth(100);
            score = 0;
		
	}
	     public static void updateDifficulty(){
            if(Options.easy = true){
                    spawnTime = 2000;
                    System.out.println("spawnTimer in easy mode.");
                    
            }

            else if(Options.normal = true){
                    spawnTime = 700;
                    System.out.println("spawnTimer in normal mode.");
                    
            }

            else if(Options.hard = true){
                    spawnTime = 1;
                    System.out.println("spawnTimer in hard mode.");
                   
            }

            else{
                    spawnTime = 700;
                    System.out.println("spawnTimer in normal mode.");
            }
	    }
	     
	  // Difficulty setting changes
            public static void setEasy(){
                spawnTime = 1000;
                missleSpeed = 1.5;
                missleSpeeds = 0.5;
                enemySpeed = .3;
                timedShotDelay = 200;
                level=1;
               
    		
    	}
    	
    	public static void setNormal(){
                spawnTime = 500;
                missleSpeed = 2.0;
                missleSpeeds = 1.5;
                enemySpeed = 1;
                timedShotDelay = 100;
                level=2;
    	}
            
    	public static void setHard(){
                spawnTime = 50;
                missleSpeed = 2.0;
                missleSpeeds = 2.;
                enemySpeed = 1.25;
                timedShotDelay = 10;
                level=3;
    	}
	

}
