/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collision;

import Bullets.Bullet;
import Bullets.BulletManager;
import java.util.List;
import ships.EnemyShip;
import ships.PlayerShip;
import utilities.Pool;

/**
 *
 * @author Erik
 */
public class CollisionManager {

    public CollisionManager() {
    }

    public void doCollision(PlayerShip player, List<EnemyShip> enemies, BulletManager bulletMan) {

        Pool playerBullets = bulletMan.playerBullets;
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet) playerBullets.getItems().get(i);
            for (int j = 0; j < enemies.size(); j++) {
                EnemyShip e = enemies.get(j);
                if (b.intersects(e)) {
                    e.health--;
                    playerBullets.push(i--);
                    if (e.health <= 0) {
                        enemies.remove(j--);
                    }
                    break;
                }
            }
        }
    }
}
