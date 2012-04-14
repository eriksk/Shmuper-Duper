/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullets;

import audio.AudioManager;
import content.ContentManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import shmuperduper.ShmuperDuper;
import utilities.ListUtils;
import utilities.Pool;

/**
 *
 * @author Erik
 */
public class BulletManager {

    public Pool playerBullets;
    public Pool enemyBullets;
    private int width, height;
    private Image texture;
    private SpriteSheet sheet;

    public BulletManager() {
        playerBullets = new Pool(256);
        enemyBullets = new Pool(2048);
        width = ShmuperDuper.width;
        height = ShmuperDuper.height;
    }

    public void load(ContentManager content) {
        texture = content.loadImage("gfx/bullets.png");
        sheet = new SpriteSheet(texture, 64, 64);

        playerBullets.init(ListUtils.allocate(playerBullets.getCapacity(), Bullet.class));
        enemyBullets.init(ListUtils.allocate(enemyBullets.getCapacity(), Bullet.class));

    }
    
    public void reset(){
        playerBullets.clear();
        enemyBullets.clear();
    }
    
    public void update(float dt) {
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet) playerBullets.getItems().get(i);
            b.x += b.vx * dt;
            b.y += b.vy * dt;
            if (b.y < -200 || b.y > height + 200
                    || b.x < -200 || b.x > width + 200) {
                playerBullets.push(i--);
            }
        }
        for (int i = 0; i < enemyBullets.getCount(); i++) {
            Bullet b = (Bullet) enemyBullets.getItems().get(i);
            b.x += b.vx * dt;
            b.y += b.vy * dt;
            if (b.y < -200 || b.y > height + 200
                    || b.x < -200 || b.x > width + 200) {
                enemyBullets.push(i--);
            }
        }
    }

    public void drawEnemyBullets(Graphics g) {
        texture.startUse();
        for (int i = 0; i < enemyBullets.getCount(); i++) {
            Bullet b = (Bullet) enemyBullets.getItems().get(i);
            sheet.renderInUse((int) b.x, (int) b.y, b.srcCol, b.srcRow);
        }
        texture.endUse();
        if (ShmuperDuper.debug) {
            for (int i = 0; i < enemyBullets.getCount(); i++) {
                Bullet b = (Bullet) enemyBullets.getItems().get(i);
                g.drawRect(b.x + b.hitbox.x, b.y + b.hitbox.y, b.hitbox.width, b.hitbox.height);
            }
        }
    }

    public void drawPlayerBullets(Graphics g) {
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet) playerBullets.getItems().get(i);
            texture.setCenterOfRotation((b.srcCol * 64) + 32f, (b.srcRow * 64) + 32f);
            texture.setRotation((float) Math.toDegrees(b.rotation));
            texture.draw((int) b.x, (int) b.y, (int) b.x + 64, (int) b.y + 64, 0, 0, 64, 64);
        }

        if (ShmuperDuper.debug) {
            for (int i = 0; i < playerBullets.getCount(); i++) {
                Bullet b = (Bullet) playerBullets.getItems().get(i);
                g.drawRect(b.x + b.hitbox.x, b.y + b.hitbox.y, b.hitbox.width, b.hitbox.height);
            }
        }
    }
}
