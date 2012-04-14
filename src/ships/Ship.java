/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import Bullets.BulletEmitter;
import Bullets.BulletManager;
import entities.Entity;
import geoms.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class Ship{
    public static Vector2f shadowOffset = new Vector2f(-16f, 46f);
    public static Color shadowColor = new Color(0,0,0, 100);
    public static float shadowScale = 1.4f;
    
    public int health;
    public BulletEmitter[] emitters;
    
    public float x, y, vx, vy, ox, oy;
    public int srcCol, srcRow;
    public Rectangle hitbox;
    
    public Ship(int srcCol, int srcRow) {
        this.srcCol = srcCol;
        this.srcRow = srcRow;
        hitbox = new Rectangle();
        emitters = new BulletEmitter[0];
        ox = 32;
        oy = 32;
    }
    
    public void update(float dt, BulletManager bulletMan) {
    }

    public void drawShadow(){
        /*
        image.drawFlash(
                (x - source.width) + shadowOffset.x, 
                (y - source.height) + shadowOffset.y,
                image.getWidth() / shadowScale, 
                image.getHeight() / shadowScale, shadowColor);
    */
    }
}
