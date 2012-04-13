/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import Bullets.BulletEmitter;
import Bullets.BulletManager;
import entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class Ship extends Entity{
    public static Vector2f shadowOffset = new Vector2f(-16f, 46f);
    public static Color shadowColor = new Color(0,0,0, 100);
    public static float shadowScale = 1.4f;
    
    public int health;
    public BulletEmitter[] emitters;
    
    public Ship(Image image) {
        super(image);
        emitters = new BulletEmitter[0];
    }
    
    public void clamp(float x, float y, float w, float h){
        this.x = Util.clamp(this.x, x, w);
        this.y = Util.clamp(this.y, y, h);
    }

    public void update(float dt, BulletManager bulletMan) {
    }

    public void drawShadow(){
        image.drawFlash(
                (x - ox) + shadowOffset.x, 
                (y - oy) + shadowOffset.y,
                image.getWidth() / shadowScale, 
                image.getHeight() / shadowScale, shadowColor);
    }
    
    @Override
    public void draw() {
        super.draw();
    }
}
