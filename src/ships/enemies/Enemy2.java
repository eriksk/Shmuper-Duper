/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.enemies;

import Bullets.BulletEmitter;
import ships.EnemyShip;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class Enemy2 extends EnemyShip{

    public Enemy2() {
        super(2, 0);
        float angle = (float)Math.toRadians(15);
        if(Util.Rand(2) == 0){
            angle = -angle;
        }
        emitters = new BulletEmitter[]{
           new BulletEmitter(80f, 24, 0, 0, 0.15f, 0f, false, angle, 2000f, 2)
        };
        
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 64;
        hitbox.height = 64;        
        health = 80;
    }
    
}
