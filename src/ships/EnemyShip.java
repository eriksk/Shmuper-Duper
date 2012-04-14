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
           new BulletEmitter(400f, 1, 0, 0, 0.15f)
        };
        
        hitbox.x = 8;
        hitbox.y = 8;
        hitbox.width = 46;
        hitbox.height = 46;
        
        health = 40;
    }
    
    private void setNewTarget(){
        target.x = Util.Rand(ShmuperDuper.width);
        target.y = Util.Rand(ShmuperDuper.height / 2f);
    }

    public void update(float dt, BulletManager bulletMan) {
        for (int i = 0; i < emitters.length; i++) {
            BulletEmitter be = emitters[i];
            if (be.fire()) {
                bulletMan.fireEnemy(x + be.offsetX, y + be.offsetY, be.speed, (float)Math.toRadians(90));
            }
        }
        for (int i = 0; i < emitters.length; i++) {
            emitters[i].update(dt);
        }
        
        x = Util.Lerp(x, target.x, speed * dt);
        y = Util.Lerp(y, target.y, speed * dt);
        
        if(Util.distance(x, y, target.x, target.y) < 24f){
            setNewTarget();
        }
    }
}
