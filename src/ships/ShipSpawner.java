/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import content.ContentManager;
import ships.enemies.*;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ShipSpawner {

    int level;
    int width, height;
    int bossInterval = 5;
    int allowedEnemies = 1;
    private int count;

    public ShipSpawner(int width, int height) {
        this.width = width;
        this.height = height;
        reset();
    }
    
    public void reset(){
        level = 1;
        count = 1;
        allowedEnemies = 1;
    }
    
    public void update(float dt, ShipManager shipMan, ContentManager content){
        if(shipMan.enemies.isEmpty()){
            level++;
            if(level % bossInterval == 0){                
                EnemyShip e = getShip(4);
                e.x = Util.Rand(width);
                e.y = -200f;
                shipMan.enemies.add(e);
                allowedEnemies++; 
                count -= bossInterval / 2;
            }else{                
                count++;
                for (int i = 0; i < count; i++) {
                    int s = 1 + Util.Rand(allowedEnemies);
                    while(s == 4){ // no bosses here
                        s = 1 + Util.Rand(allowedEnemies);
                    }
                    EnemyShip e = getShip(s);
                    e.x = Util.Rand(width);
                    e.y = -400;
                    shipMan.enemies.add(e);
                }
            }
        }
    }
    
    private EnemyShip getShip(int type){
        switch (type) {
            case 1: return new Enemy1();
            case 2: return new Enemy2();
            case 3: return new Enemy3();
            case 4: return new Boss1();
            default:
                return null;
        }
    }
}
