/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import Bullets.BulletEmitter;
import Bullets.BulletManager;
import org.newdawn.slick.geom.Vector2f;
import shmuperduper.ShmuperDuper;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class EnemyShip extends Ship {

    Vector2f target;
    float speed = 0.0002f;
    
    public EnemyShip(int srcCol, int srcRow) {
        super(srcCol, srcRow);
        target = new Vector2f();
        setNewTarget();
        
        emitters = new BulletEmitter[]{
           new BulletEmitter(100f, 3, 0, 0, 0.15f, 0f, true, 0f, 1000f)
        };
        
        hitbox.x = 8;
        hitbox.y = 8;
        hitbox.width = 46;
        hitbox.height = 46;        
        health = 40;
        speed = Util.Rand(0.0002f, 0.0008f);
    }
    
    private void setNewTarget(){
        target.x = Util.Rand(ShmuperDuper.width);
        target.y = Util.Rand(ShmuperDuper.height / 2f);
    }

    public void update(float dt, BulletManager bulletMan, ShipManager shipMan) {
        for (int i = 0; i < emitters.length; i++) {
            BulletEmitter be = emitters[i];
            emitters[i].update(dt, x + be.offsetX, y + be.offsetY, bulletMan.enemyBullets, 22, 22, 18, 18, 1, 0, shipMan.player, "fire_enemy");
        }
        
        x = Util.Lerp(x, target.x, speed * dt);
        y = Util.Lerp(y, target.y, speed * dt);
        
        if(Util.distance(x, y, target.x, target.y) < 24f){
            setNewTarget();
        }
    }
}
