/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import Bullets.BulletEmitter;
import Bullets.BulletManager;
import input.InputManager;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Erik
 */
public class PlayerShip extends Ship {

    private float speed = 0.3f;
    public int score = 0;

    public PlayerShip() {
        super(0, 0);
        emitters = new BulletEmitter[]{
            new BulletEmitter(40f, 6, -24f, -10f, 2f, (float) Math.toRadians(-110), false, 0f, 60f),
            new BulletEmitter(20f, 4, -10f, 0, 3f, (float) Math.toRadians(-90), false, 0f, 60f),
            new BulletEmitter(20f, 4, 10f, 0, 3f, (float) Math.toRadians(-90), false, 0f, 60f),
            new BulletEmitter(40f, 6, 24f, -10f, 2f, (float) Math.toRadians(-70), false, 0f, 60f)
        };
        health = 1000;
        hitbox.x = 24;
        hitbox.y = 24;
        hitbox.width = 18;
        hitbox.height = 18;
    }

    public void update(float dt, BulletManager bulletMan, ShipManager shipMan) {
        if (InputManager.I().isKeyDown(Keyboard.KEY_SPACE)) {
            for (int i = 0; i < emitters.length; i++) {
                BulletEmitter be = emitters[i];
                be.update(dt, x + be.offsetX, y + be.offsetY, bulletMan.playerBullets, 16, 16, 24, 24, 0, 0, this, "fire");
            }
        }

        if (InputManager.I().isKeyDown(Keyboard.KEY_LEFT)) {
            x -= speed * dt;
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_RIGHT)) {
            x += speed * dt;
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_UP)) {
            y -= speed * dt;
        }
        if (InputManager.I().isKeyDown(Keyboard.KEY_DOWN)) {
            y += speed * dt;
        }
    }
}
