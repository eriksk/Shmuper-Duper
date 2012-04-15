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
    protected float speed = 0.0002f;
    private static float entrySpeed = 0.0009f;

    public EnemyShip(int srcCol, int srcRow, int cols, int rows) {
        this(srcCol, srcRow);
        this.cols = cols;
        this.rows = rows;
    }

    public EnemyShip(int srcCol, int srcRow) {
        super(srcCol, srcRow);
        target = new Vector2f();
        setNewTarget();

        emitters = new BulletEmitter[]{};
        hitbox.x = 8;
        hitbox.y = 8;
        hitbox.width = 46;
        hitbox.height = 46;
        health = 40;
        speed = Util.Rand(0.0002f, 0.0008f);
    }

    private void setNewTarget() {
        target.x = 100 + Util.Rand(ShmuperDuper.width - 200);
        target.y = 50 + Util.Rand((ShmuperDuper.height / 2f) - 200);
    }

    public void update(float dt, BulletManager bulletMan, ShipManager shipMan) {
        for (int i = 0; i < emitters.length; i++) {
            BulletEmitter be = emitters[i];
            emitters[i].update(dt, x + be.offsetX, y + be.offsetY, bulletMan.enemyBullets, 22, 22, 18, 18, shipMan.player, "fire_enemy");
        }
        if (y < 50) {
            x = Util.Lerp(x, target.x, entrySpeed * dt);
            y = Util.Lerp(y, target.y, entrySpeed * dt);
        } else {
            x = Util.Lerp(x, target.x, speed * dt);
            y = Util.Lerp(y, target.y, speed * dt);
        }
        
        if (Util.distance(x, y, target.x, target.y) < 24f) {
            setNewTarget();
        }
    }
}
