/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import content.ContentManager;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ShipSpawner {

    int count;
    int width, height;

    public ShipSpawner(int width, int height) {
        this.width = width;
        this.height = height;
        reset();
    }
    
    public void reset(){
        count = 1;
    }
    
    public void update(float dt, ShipManager shipMan, ContentManager content){
        if(shipMan.enemies.isEmpty()){
            count++;
            for (int i = 0; i < count; i++) {
                EnemyShip e = new EnemyShip(1, 0);
                e.x = Util.Rand(width);
                e.y = -Util.Rand(200, 400);
                shipMan.enemies.add(e);
            }
        }
    }
}
