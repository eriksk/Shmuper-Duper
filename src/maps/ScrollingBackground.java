/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import content.ContentManager;
import entities.Entity;
import java.util.ArrayList;
import java.util.List;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ScrollingBackground {

    List<Entity> objects;
    public float scrollSpeed = 0.5f;
    int width, height;
    
    public ScrollingBackground(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void load(ContentManager content){
        objects = new ArrayList<Entity>();
        for (int i = 0; i < 50; i++) {
            Entity e = new Entity(content.loadImage("gfx/bg" + 1 + ".png"));
            e.x = -500 + (Util.Rand(width + 500));
            e.y = Util.Rand(height);
            objects.add(e);            
        }
    }
    
    public void update(float dt){
        for (int i = 0; i < objects.size(); i++) {
            Entity e = objects.get(i);
            e.y += scrollSpeed;
            if(e.y > height + e.getImage().getHeight()){
                e.y = -Util.Rand(500);
                e.y -= e.getImage().getHeight();
            }
        }
    }
    
    public void draw(){
        for (int i = 0; i < objects.size(); i++) {
            Entity e = objects.get(i);
            e.draw();
        }
    }
}
