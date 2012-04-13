/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullets;

/**
 *
 * @author Erik
 */
public class BulletEmitter {
    
    private float current, interval;
    private int fireCount;
    public float offsetX, offsetY;
    public float speed;

    public BulletEmitter(float interval, int fireCount, float offsetX, float offsetY, float speed) {
        this.interval = interval;
        this.fireCount = fireCount;
        current = 0f;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.speed = speed;
    }
    
    public boolean fire(){
        if(current > interval){
            current = 0f;
            return true;
        }
        return false;
    }

    public void update(float dt){
        current += dt;
    }
}
