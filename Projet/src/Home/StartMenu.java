package Home;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class StartMenu extends BasicGameState {
    private Image[] bgArray;
    private float countX = 0;
    
    public int getID() 
    {
        return 0;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        bgArray = new Image[3];
        bgArray[0] = new Image("images/background.jpg");
        bgArray[1] = new Image("images/background.jpg");
        bgArray[2] = new Image("images/background.jpg");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        if (gc.getInput().isKeyPressed(Input.KEY_2)) {
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if (countX < 1700) {
            countX += .05;
        }
        else {
            countX = -1700;
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        int bgCounter = -1;
        for (Image bg : bgArray) {
            g.drawImage(bg, bgCounter * 1700 + countX, 0);
            bgCounter++;
        }
        
        g.setColor(Color.black);
        g.fillRect(770, 390, 140, 90);
        g.setColor(Color.blue);
        g.fillRect(780, 380, 140, 90);
        g.setColor(Color.white);
        g.drawString("shooterSpace", 795, 400);
        g.drawString("1. Play", 795, 420);
        g.drawString("2. Options", 795, 440);
    } 
}
