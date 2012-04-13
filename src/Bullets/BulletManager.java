/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullets;

import content.ContentManager;
import java.util.ArrayList;
import java.util.List;
import shmuperduper.ShmuperDuper;
import utilities.Pool;

/**
 *
 * @author Erik
 */
public class BulletManager {

    public Pool playerBullets;
    public Pool enemyBullets;
    private int width, height;
    
    public BulletManager() {
        playerBullets = new Pool(256);
        enemyBullets = new Pool(256);
        width = ShmuperDuper.width;
        height = ShmuperDuper.height;
    }
    
    public void load(ContentManager content){
        List<Object> b = new ArrayList<Object>();
        for (int i = 0; i < playerBullets.getCapacity(); i++) {
            b.add(new Bullet(content.loadImage("gfx/playerbullet.png")));
        }
        playerBullets.init(b);
        
        List<Object> e = new ArrayList<Object>();
        for (int i = 0; i < enemyBullets.getCapacity(); i++) {
            e.add(new Bullet(content.loadImage("gfx/enemybullet.png")));
        }
        enemyBullets.init(e);
    }
    
    public void firePlayer(float x, float y, float speed, float angle){
        Bullet b = (Bullet)playerBullets.pop();
        b.x = x;
        b.y = y;
        b.vx = (float)Math.cos(angle) * speed;
        b.vy = (float)Math.sin(angle) * speed;
        b.getImage().setRotation((float)Math.toDegrees(angle) + 90f);
    }
    public void fireEnemy(float x, float y, float speed, float angle){
        Bullet b = (Bullet)enemyBullets.pop();
        b.x = x;
        b.y = y;
        b.vx = (float)Math.cos(angle) * speed;
        b.vy = (float)Math.sin(angle) * speed;
        b.getImage().setRotation((float)Math.toDegrees(angle) + 90f);
    }
    
    public void update(float dt){
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet)playerBullets.getItems().get(i);
            b.x += b.vx * dt;
            b.y += b.vy * dt;
            if(b.y < 0f || b.y > height ||
               b.x < 0f || b.x > width){
                playerBullets.push(i--);
            }
        }
        for (int i = 0; i < enemyBullets.getCount(); i++) {
            Bullet b = (Bullet)enemyBullets.getItems().get(i);
            b.x += b.vx * dt;
            b.y += b.vy * dt;
            if(b.y < 0f || b.y > height ||
               b.x < 0f || b.x > width){
                enemyBullets.push(i--);
            }
        }
    }
    
    public void draw(){
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet)playerBullets.getItems().get(i);
            b.draw();
        }
        for (int i = 0; i < enemyBullets.getCount(); i++) {
            Bullet b = (Bullet)enemyBullets.getItems().get(i);
            b.draw();
        }
    }
}
