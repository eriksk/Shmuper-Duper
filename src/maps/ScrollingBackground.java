/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maps;

import content.ContentManager;
import entities.Entity;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Image;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ScrollingBackground {

    public float scrollSpeed = 0.08f;
    int width, height;
    Image bgImage, mask;
    float y = 0f;
    
    public ScrollingBackground(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void load(ContentManager content){
        bgImage = content.loadImage("gfx/water.png");
        mask = content.loadImage("gfx/water_transp.png");        
    }
    
    public void update(float dt){
        
        y += scrollSpeed * dt;
        if(y > height){
            y = 0f;
        }
    }
    
    public void draw(){
        bgImage.draw(0f, 0f);
        mask.draw(0f, y - height);
        mask.draw(0f, y);
    }
}
