/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.newdawn.slick.Image;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class Entity {

    public static int instances;
    public int id;
    protected Image image;
    public float x, y, vx, vy;
    public float ox, oy;

    public Entity(Image image) {
        setImage(image);
        setId();
    }
    
    private void setId(){
        id = instances++;
    }

    public void setImage(Image image) {
        this.image = image;
        ox = image.getWidth() / 2f;
        oy = image.getHeight() / 2f;
    }

    public Image getImage() {
        return image;
    }
    
    public boolean intersects(Entity other){
        if(other.x + other.getImage().getWidth() < x) return false;
        if(other.y + other.getImage().getHeight() < y) return false;
        if(x + image.getWidth() < other.x) return false;
        if(y + image.getHeight() < other.y) return false;
        
        return true;
    }

    public void update(float dt){
    }
    
    public void draw(){
        image.draw(x - ox, y - oy);
    }
}
