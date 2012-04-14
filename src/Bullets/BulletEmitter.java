/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullets;

import audio.AudioManager;
import ships.PlayerShip;
import utilities.Pool;
import utilities.Util;

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

    public BulletEmitter(
            float interval,
            int fireCount,
            float offsetX,
            float offsetY,
            float speed,
            float angle,
            boolean targeted,
            float angleDiff,
            float wait) {
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
        fired = 0;
    }

    public void update(float dt, float x, float y, Pool bulletPool, float srcX, float srcY, float srcW, float srcH, int col, int row, PlayerShip player, String sound) {
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
                    b.srcCol = col;
                    b.srcRow = row;
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
                }
            }
        } else {
            currentWait += dt;
        }
    }
}
