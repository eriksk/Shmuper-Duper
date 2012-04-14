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
import org.newdawn.slick.geom.Vector2f;
import shmuperduper.ShmuperDuper;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ShipManager {

    public static Vector2f shadowOffset = new Vector2f(-64, 0);
    public static Color shadowColor = new Color(0, 0, 0, 100);
    public static float shadowScale = 1.4f;
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
        enemies = new ArrayList<EnemyShip>();
        reset();
    }
    
    public void reset(){
        enemies.clear();
        player.x = width / 2f;
        player.y = height - 200f;
    }

    public void update(float dt, BulletManager bulletMan) {
        player.update(dt, bulletMan, this);
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            e.update(dt, bulletMan, this);
        }
        
        if(player.x < -64){
            player.x = -64;
        }
        if(player.x > width){
            player.x =  width;
        }
        if(player.y < -64){
            player.y = -64;
        }
        if(player.y > height){
            player.y =  height;
        }
    }

    public void drawShadows() {
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            sheet.draw(
                    (int) (e.x + shadowOffset.x),
                    (int) (e.y + shadowOffset.y),
                    (int) (e.x + shadowOffset.x) + 128,
                    (int) (e.y + shadowOffset.y) + 128,
                    128 * e.srcCol,
                    128 * e.srcRow,
                    (128 * e.srcCol) + 128,
                    (128 * e.srcRow) + 128,
                    shadowColor);
        }
        sheet.draw(
                (int) (player.x + shadowOffset.x),
                (int) (player.y + shadowOffset.y),
                (int) (player.x + shadowOffset.x) + 128,
                (int) (player.y + shadowOffset.y) + 128,
                128 * player.srcCol,
                128 * player.srcRow,
                (128 * player.srcCol) + 128,
                (128 * player.srcRow) + 128,
                shadowColor);
    }

    public void drawEnemies(Graphics g) {
        texture.startUse();
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
