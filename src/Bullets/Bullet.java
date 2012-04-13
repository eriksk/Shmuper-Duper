/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullets;

import entities.Entity;
import org.newdawn.slick.Image;

/**
 *
 * @author Erik
 */
public class Bullet extends Entity {
    public int owner;

    public Bullet(Image image) {
        super(image);
    }

    @Override
    public void update(float dt) {
        x += vx * dt;
        y += vy * dt;
    }   
}
