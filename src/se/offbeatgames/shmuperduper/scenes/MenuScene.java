/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.scenes;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import se.offbeatgames.ld48lib.content.ContentManager;
import se.offbeatgames.ld48lib.geoms.Rectangle;
import se.offbeatgames.ld48lib.input.InputManager;
import se.offbeatgames.ld48lib.scenes.Scene;
import se.offbeatgames.ld48lib.scenes.SceneManager;
import se.offbeatgames.shmuperduper.shmuperduper.ShmuperDuper;

/**
 *
 * @author Erik
 */
public class MenuScene extends Scene{

    private Image logo;
    private float buttonX = (ShmuperDuper.width / 2f) - 48;
    private float buttonY = (ShmuperDuper.height / 2f) + 200;
    
    public MenuScene(SceneManager manager) {
        super(manager);
    }

    @Override
    public void onActivated() {
    }
    
    @Override
    public void load(GameContainer container) {
        super.load(container);
        logo = new ContentManager("resources/").loadImage("gfx/logo.png");
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        
        float x = Mouse.getX();
        float y = ShmuperDuper.height - Mouse.getY();
        
        if(Mouse.isButtonDown(0)){
            if(new Rectangle(buttonX, buttonY, 100, 50).contains(x, y)){
                manager.setCurrentScene("game");
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.white);
        g.fillRect(0, 0, ShmuperDuper.width, ShmuperDuper.height);
        
        logo.draw(20, 20);
        drawButton(g, "Play", buttonX, buttonY);
    }   
    
    private void drawButton(Graphics g, String text, float x, float y){
        g.setColor(new Color(25, 174, 255));
        g.fillRect(x, y, 100, 48);
        g.setColor(Color.black);
        g.drawRect(x - 1, y - 1, 101, 49);
        g.drawString(text, x + 30, y + 14);
    }
}
