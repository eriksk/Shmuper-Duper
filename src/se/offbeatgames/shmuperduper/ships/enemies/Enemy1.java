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
public class Enemy1 extends EnemyShip{

    public Enemy1() {
        super(1, 0);
        
        emitters = new BulletEmitter[]{
           new BulletEmitter(100f, 2, 0, 0, 0.15f, 0f, true, 0f, 2000f, 1)
        };
    }
    
}
