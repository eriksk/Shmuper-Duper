/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.offbeatgames.shmuperduper.collision;

import se.offbeatgames.shmuperduper.bullets.Bullet;
import se.offbeatgames.shmuperduper.bullets.BulletManager;
import se.offbeatgames.ld48lib.audio.AudioManager;
import java.util.List;
import se.offbeatgames.ld48lib.particles.Particle;
import se.offbeatgames.shmuperduper.particles.ParticleManagerImpl;
import se.offbeatgames.shmuperduper.ships.EnemyShip;
import se.offbeatgames.shmuperduper.ships.PlayerShip;
import se.offbeatgames.shmuperduper.ships.Ship;
import se.offbeatgames.shmuperduper.shmuperduper.GameManager;
import se.offbeatgames.ld48lib.utilities.Pool;

/**
 *
 * @author Erik
 */
public class CollisionManager {

    private GameManager game;
    
    public CollisionManager(GameManager game) {
        this.game = game;
    }

    public void doCollision(PlayerShip player, List<EnemyShip> enemies, BulletManager bulletMan, ParticleManagerImpl pMan) {
        // playerbullets vs enemies
        Pool playerBullets = bulletMan.playerBullets;
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet) playerBullets.getItems().get(i);
            for (int j = 0; j < enemies.size(); j++) {
                EnemyShip e = enemies.get(j);
                if (intersects(b, e)){
                    AudioManager.I().playSound("hit");
                    pMan.spawn(b.x, b.y - 64);
                    e.health--;
                    playerBullets.push(i--);
                    if (e.health <= 0) {
                        pMan.explode(e.x, e.y);
                        AudioManager.I().playSound("explosion");
                        enemies.remove(j--);
                    }
                    break;
                }
            }
        }
        
        // enemybullets vs player        
        Pool enemyBullets = bulletMan.enemyBullets;
        for (int i = 0; i < enemyBullets.getCount(); i++) {
            Bullet b = (Bullet) enemyBullets.getItems().get(i);
            if(intersects(b, player)){
                game.gameOver();
                break;
            }
        }
    }
    
    public static boolean intersects(Bullet b, Ship s){                
        if((b.y + b.hitbox.y) + b.hitbox.height < s.y + s.hitbox.y) return false;
        if((s.y + s.hitbox.y) + s.hitbox.height < b.y + b.hitbox.y) return false;        
        if((b.x + b.hitbox.x) + b.hitbox.width < s.x + s.hitbox.x) return false;
        if((s.x + s.hitbox.x) + s.hitbox.width < b.x + b.hitbox.x) return false;
        return true;
    }
    public static boolean intersects(Particle b, Ship s){                
        if(b.y + 32 < s.y + s.hitbox.y) return false;
        if((s.y + s.hitbox.y) + s.hitbox.height < b.y) return false;        
        if(b.x + 32 < s.x + s.hitbox.x) return false;
        if((s.x + s.hitbox.x) + s.hitbox.width < b.x) return false;
        return true;
    }
}
