/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bullets;

import content.ContentManager;
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

    public void firePlayer(float x, float y, float speed, float angle) {
        Bullet b = (Bullet) playerBullets.pop();
        b.x = x;
        b.y = y;
        b.vx = (float) Math.cos(angle) * speed;
        b.vy = (float) Math.sin(angle) * speed;
        b.rotation = angle;
        b.srcCol = 0;
        b.srcRow = 0;
        b.hitbox.x = 16;
        b.hitbox.y = 16;
        b.hitbox.width = 24;
        b.hitbox.height = 24;
    }

    public void fireEnemy(float x, float y, float speed, float angle) {
        Bullet b = (Bullet) enemyBullets.pop();
        b.x = x;
        b.y = y;
        b.vx = (float) Math.cos(angle) * speed;
        b.vy = (float) Math.sin(angle) * speed;
        b.rotation = angle;
        b.srcCol = 1;
        b.srcRow = 0;
        b.hitbox.x = 16;
        b.hitbox.y = 16;
        b.hitbox.width = 32;
        b.hitbox.height = 32;

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
                g.drawRect(b.x + 16, b.y + 16, 32, 32);
            }
        }
    }

    public void drawPlayerBullets(Graphics g) {
        texture.startUse();
        for (int i = 0; i < playerBullets.getCount(); i++) {
            Bullet b = (Bullet) playerBullets.getItems().get(i);
            sheet.renderInUse((int) b.x, (int) b.y, b.srcCol, b.srcRow);
        }
        texture.endUse();

        if (ShmuperDuper.debug) {
            for (int i = 0; i < playerBullets.getCount(); i++) {
                Bullet b = (Bullet) playerBullets.getItems().get(i);
                g.drawRect(b.x + 16, b.y, 24, 64);
            }
        }
    }
}
