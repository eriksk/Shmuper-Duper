/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.scenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;
import se.offbeatgames.shmuperduper.actions.GameOverListener;
import se.offbeatgames.shmuperduper.shmuperduper.GameManager;
import se.offbeatgames.shmuperduper.shmuperduper.ShmuperDuper;

/**
 *
 * @author Erik
 */
public class GameScene extends Scene {

    GameManager gm;

    public GameScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void onActivated() {
        gm.start();
    }
    
    @Override
    public void load(GameContainer container) {
        super.load(container);
        gm = new GameManager(ShmuperDuper.width, ShmuperDuper.height, new GameOverListener() {
            @Override
            public void onGameOver(int score) {
                manager.setCurrentScene("menu");
            }
        });
        gm.load(container);
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        gm.update(dt);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        gm.draw(g);
    }
}
