package Home;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameOver extends Game {
   
	public int getID() {
        return 4;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    	Game.updatePlay();
    	
        if (gc.getInput().isKeyPressed(Input.KEY_Y)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());            
        }
        
        if (gc.getInput().isKeyPressed(Input.KEY_N)) {
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawString("GAME OVER!", 795, 400);
        g.drawString("Your final score was " + finalScore, 795, 440);
        g.drawString("Try again? (Y/N)", 795, 460);
    }
}

