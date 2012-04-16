/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.ships;

import se.offbeatgames.shmuperduper.bullets.BulletEmitter;
import se.offbeatgames.shmuperduper.bullets.BulletManager;
import se.offbeatgames.ld48lib.entities.Entity;
import se.offbeatgames.ld48lib.geoms.Rectangle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import se.offbeatgames.ld48lib.utilities.Util;

/**
 *
 * @author Erik
 */
public class Ship{
    
    public int health;
    public BulletEmitter[] emitters;
    
    public float x, y, vx, vy, ox, oy;
    public int srcCol, srcRow;
    public Rectangle hitbox;
    public int cols, rows;
    
    public Ship(int srcCol, int srcRow) {
        this.srcCol = srcCol;
        this.srcRow = srcRow;
        hitbox = new Rectangle();
        emitters = new BulletEmitter[0];
        ox = 32;
        oy = 32;
        cols = 1;
        rows = 1;
    }
    
    public void update(float dt, BulletManager bulletMan) {
    }
}
