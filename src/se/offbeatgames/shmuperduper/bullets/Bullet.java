/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.bullets;

import se.offbeatgames.ld48lib.geoms.Rectangle;

/**
 *
 * @author Erik
 */
public class Bullet{
    public int owner;

    public float x, y, vx, vy, rotation, ox, oy;
    public int srcCol, srcRow;
    public Rectangle hitbox;
    
    public Bullet() {
        ox = 32;
        oy = 32;
        hitbox = new Rectangle();
    }
}
