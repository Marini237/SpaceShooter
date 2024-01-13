package Home;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Mainspace  extends StateBasedGame{
	public Mainspace(String name) {
		super(name);
	}

	public static int lives = 3;
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Mainspace("Space Shooter"));
		app.setDisplayMode(1700,850, false);
		app.setShowFPS(false);
		app.setIcon("images/icon32.png");
		app.start();
	}
	public void initStatesList(GameContainer gc) throws SlickException {
        addState(new StartMenu());
        addState(new GameOver());
        addState(new Options());
        addState(new Game());
        addState(new Pause());  
    }

}
