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

    public PlayerShip() {
        super(0, 0);
        emitters = new BulletEmitter[]{
            new BulletEmitter(40f, 1, -27f, 0, 2f),
            new BulletEmitter(20f, 1, -10f, 0, 3f),
            new BulletEmitter(20f, 1, 10f, 0, 3f),
            new BulletEmitter(40f, 1, 27f, 0, 2f)
        };
        health = 1000;
        hitbox.x = 16;
        hitbox.y = 16;
        hitbox.width = 32;
        hitbox.height = 32;
    }

    @Override
    public void update(float dt, BulletManager bulletMan) {
        if (InputManager.I().isKeyDown(Keyboard.KEY_SPACE)) {
            for (int i = 0; i < emitters.length; i++) {
                BulletEmitter be = emitters[i];
                if (be.fire()) {
                    bulletMan.firePlayer(x + be.offsetX, y + be.offsetY, be.speed, (float)Math.toRadians(-90f));
                }
            }
        }
        for (int i = 0; i < emitters.length; i++) {
            emitters[i].update(dt);
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
