/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.bullets;

import se.offbeatgames.ld48lib.audio.AudioManager;
import se.offbeatgames.shmuperduper.ships.PlayerShip;
import se.offbeatgames.ld48lib.utilities.Pool;
import se.offbeatgames.ld48lib.utilities.Util;

/**
 *
 * @author Erik
 */
public class BulletEmitter {

    private float current, interval;
    public float currentWait;
    public int fireCount;
    private int fired;
    public float offsetX, offsetY;
    public float speed;
    public float angle;
    public boolean targeted;
    public float angleDiff;
    public float wait;
    private float startAngle;
    private int type;

    public BulletEmitter(
            float interval,
            int fireCount,
            float offsetX,
            float offsetY,
            float speed,
            float angle,
            boolean targeted,
            float angleDiff,
            float wait,
            int type) {
        this.interval = interval;
        this.fireCount = fireCount;
        current = 0f;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.speed = speed;
        this.angle = angle;
        this.targeted = targeted;
        this.angleDiff = angleDiff;
        this.wait = wait;
        this.startAngle = angle;
        this.type = type;
        fired = 0;
    }

    public void update(float dt, float x, float y, Pool bulletPool, float srcX, float srcY, float srcW, float srcH, PlayerShip player, String sound) {
        if (currentWait > wait) {
            current += dt;
            if (current > interval) {
                current = 0f;
                if (fired < fireCount) {
                    Bullet b = (Bullet) bulletPool.pop();
                    b.x = x;
                    b.y = y;
                    if (targeted) {
                        angle = (float) Math.atan2(player.y - y, player.x - x);
                    }
                    b.vx = (float) Math.cos(angle) * speed;
                    b.vy = (float) Math.sin(angle) * speed;
                    b.rotation = angle;                    
                    b.srcCol = type % 8;
                    b.srcRow = type / 8;
                    b.hitbox.x = srcX;
                    b.hitbox.y = srcY;
                    b.hitbox.width = srcW;
                    b.hitbox.height = srcH;
                    fired++;
                    angle += angleDiff;
                    AudioManager.I().playSound(sound);
                } else {
                    currentWait = 0f;
                    fired = 0;
                    angle = startAngle;
                }
            }
        } else {
            currentWait += dt;
        }
    }
}
