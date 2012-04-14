/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shmuperduper;

import input.InputManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Erik
 */
public class ShmuperDuper extends BasicGame {

    public static final int width = 512;
    public static final int height = 720;
    private static final int fps = -1;
    private static final boolean fullscreen = false;
    public static boolean debug = false;
    
    GameManager gm;

    public ShmuperDuper() {
        super("Shmuper Duper");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        gm = new GameManager(width, height);
        gm.load(container);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            exit();
        }
        
        if(InputManager.I().isKeyDown(Keyboard.KEY_D)){
            debug = !debug;
        }
        
        gm.update(delta);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        gm.draw(g);
    }
    
    static AppGameContainer gc;
    public static void main(String[] args) {
        try {
            gc = new AppGameContainer(new ShmuperDuper());
            gc.setDisplayMode(width, height, fullscreen);
            gc.setTargetFrameRate(fps);
            gc.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }

    public static void exit() {
        gc.exit();
    }
}
