/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.ships.enemies;

import se.offbeatgames.shmuperduper.bullets.BulletEmitter;
import se.offbeatgames.shmuperduper.ships.EnemyShip;

/**
 *
 * @author Erik
 */
public class Boss1 extends EnemyShip{

    public Boss1() {
        super(0, 1, 2, 1);
        
        emitters = new BulletEmitter[]{
           new BulletEmitter(100f, 18, 0, 0, 0.15f, 0f, true, 0f, 2000f, 4), // homing
           
           new BulletEmitter(80f, 12, -60f, 0, 0.2f, (float)Math.toRadians(110), false, 0f, 2000f, 3), // left
           new BulletEmitter(80f, 12, 60f, 0, 0.2f, (float)Math.toRadians(70), false, 0f, 2000f, 3), // right
           
           new BulletEmitter(160f, 24, 0, 0, 0.1f, 0f, false, (float)Math.toRadians(15), 2000f, 2), // spread
        };
        
        hitbox.x = -64;
        hitbox.y = 0;
        hitbox.width = 128 + 64;
        hitbox.height = 64;        
        health = 1000;
        ox = 128f - 32;
        
        this.speed = 0.0001f;
    }
}
