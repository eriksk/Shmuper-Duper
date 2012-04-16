/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.shmuperduper;

import se.offbeatgames.ld48lib.input.InputManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;
import se.offbeatgames.shmuperduper.scenes.GameScene;
import se.offbeatgames.shmuperduper.scenes.MenuScene;
import sun.nio.cs.ext.GB18030;

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
    
    SceneManager sceneMan;
    
    public ShmuperDuper() {
        super("Shmuper Duper");
    }

    @Override
    public void init(GameContainer container) throws SlickException {       
        container.setShowFPS(false);
        //container.setMusicOn(false);
        //container.setSoundOn(false);
        sceneMan = new SceneManager();
        sceneMan.load();      
        Scene menu = new MenuScene(sceneMan);
        menu.load(container);
        sceneMan.addScene(menu, "menu");
        Scene gameScene = new GameScene(sceneMan);
        gameScene.load(container);
        sceneMan.addScene(gameScene, "game");
        sceneMan.setCurrentScene("menu");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            exit();
        }        
        if(InputManager.I().isKeyDown(Keyboard.KEY_D)){
            debug = !debug;
        }
        
        sceneMan.update(delta);        
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        sceneMan.draw(g);
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
        AL.destroy();
        gc.exit();
    }
}
