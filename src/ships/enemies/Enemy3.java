 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.enemies;

import Bullets.BulletEmitter;
import ships.EnemyShip;

/**
 *
 * @author Erik
 */
public class Enemy3 extends EnemyShip{

    public Enemy3() {
        super(3, 0);
        
        emitters = new BulletEmitter[]{
           new BulletEmitter(80f, 24, 0, 0, 0.15f, 0f, false, (float)Math.toRadians(90), 2000f, 4)
        };
        
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 64;
        hitbox.height = 64;        
        health = 150;
    }
    
}
