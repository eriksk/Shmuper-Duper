/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import Bullets.BulletEmitter;
import Bullets.BulletManager;
import input.InputManager;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Erik
 */
public class PlayerShip extends Ship {

    private float speed = 0.3f;

    public PlayerShip(Image image) {
        super(image);
        emitters = new BulletEmitter[]{
            new BulletEmitter(40f, 1, -27f, 0, 1.7f),
            new BulletEmitter(100f, 1, -10f, 0, 1.2f),
            new BulletEmitter(100f, 1, 10f, 0, 1.2f),
            new BulletEmitter(40f, 1, 27f, 0, 1.7f)
        };
        
        health = 1000;
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
