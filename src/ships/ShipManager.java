/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ships;

import Bullets.BulletManager;
import content.ContentManager;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import shmuperduper.ShmuperDuper;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ShipManager {

    private int width, height;
    public PlayerShip player;
    public List<EnemyShip> enemies;
    private Image texture;
    private SpriteSheet sheet;

    public ShipManager(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void load(ContentManager content) {
        texture = content.loadImage("gfx/ships.png");
        sheet = new SpriteSheet(texture, 128, 128);
        player = new PlayerShip();
        player.x = width / 2f;
        player.y = height - 200f;
        enemies = new ArrayList<EnemyShip>();
        for (int i = 0; i < 4; i++) {
            EnemyShip e = new EnemyShip(1, 0);
            e.x = Util.Rand(width);
            e.y = Util.Rand(height / 2f);
            enemies.add(e);
        }
    }

    public void update(float dt, BulletManager bulletMan) {
        player.update(dt, bulletMan);
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            e.update(dt, bulletMan);
        }
    }

    public void drawEnemies(Graphics g) {
        texture.startUse();
        /* for (int i = 0; i < enemies.size(); i++) {
        EnemyShip e = enemies.get(i);
        e.drawShadow();
        }
         */
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            sheet.renderInUse((int) (e.x - e.ox), (int) (e.y - e.oy), e.srcCol, e.srcRow);
        }
        texture.endUse();

        if (ShmuperDuper.debug) {
            g.setColor(Color.red);
            for (int i = 0; i < enemies.size(); i++) {
                EnemyShip e = enemies.get(i);
                g.drawRect(e.x + e.hitbox.x, e.y + e.hitbox.y, e.hitbox.width, e.hitbox.height);
            }
        }
    }

    public void drawPlayers(Graphics g) {
        texture.startUse();
        sheet.renderInUse((int) (player.x - player.ox), (int) (player.y - player.oy), player.srcCol, player.srcRow);
        texture.endUse();

        if (ShmuperDuper.debug) {
            g.drawRect(player.x + player.hitbox.x, player.y + player.hitbox.y, player.hitbox.width, player.hitbox.height);
        }
    }
}
