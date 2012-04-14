/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import Bullets.Bullet;
import Bullets.BulletManager;
import java.util.List;
import particles.ParticleManagerImpl;
import ships.EnemyShip;
import ships.PlayerShip;
import ships.Ship;
import utilities.Pool;

/**
 *
 * @author Erik
 */
public class CollisionManager {

    public CollisionManager() {
    }

    public void doCollision(PlayerShip player, List<EnemyShip> enemies, BulletManager bulletMan, ParticleManagerImpl pMan) {

        Pool playerBullets = bulletMan.playerBullets;
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet) playerBullets.getItems().get(i);
            for (int j = 0; j < enemies.size(); j++) {
                EnemyShip e = enemies.get(j);
                if (intersects(b, e)){
                    e.health--;
                    playerBullets.push(i--);
                    if (e.health <= 0) {
                        pMan.explode(e.x + e.hitbox.x + (e.hitbox.width / 2f), e.y + e.hitbox.x + (e.hitbox.height / 2f));
                        enemies.remove(j--);
                    }
                    break;
                }
            }
        }
    }
    
    private boolean intersects(Bullet b, Ship s){                
        if((b.y + b.hitbox.y) + b.hitbox.height < s.y + s.hitbox.y) return false;
        if((s.y + s.hitbox.y) + s.hitbox.height < b.y + b.hitbox.y) return false;        
        if((b.x + b.hitbox.x) + b.hitbox.width < s.x + s.hitbox.x) return false;
        if((s.x + s.hitbox.x) + s.hitbox.width < b.x + b.hitbox.x) return false;
        return true;
    }
}
