/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shmuperduper;

import Bullets.BulletManager;
import collision.CollisionManager;
import content.ContentManager;
import input.InputManager;
import java.util.ArrayList;
import java.util.List;
import maps.ScrollingBackground;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import particles.ParticleManager;
import ships.EnemyShip;
import ships.PlayerShip;
import ships.Ship;

/**
 *
 * @author Erik
 */
public class GameManager {

    private int width, height;
    private ContentManager content;
    private ScrollingBackground bg;
    private ParticleManager pm;
    private Color clearColor;
    
    // game stuff
    private PlayerShip player;
    private List<EnemyShip> enemies;
    private BulletManager bulletMan;
    private CollisionManager collMan;

    public GameManager(int width, int height) {
        this.width = width;
        this.height = height;
        clearColor = new Color(163, 139, 73);
    }

    public void load(GameContainer container) {
        content = new ContentManager("resources");
        player = new PlayerShip(content.loadImage("gfx/ship.png"));
        player.x = width / 2f;
        player.y = height - player.getImage().getHeight();

        bulletMan = new BulletManager();
        bulletMan.load(content);

        bg = new ScrollingBackground(width, height);
        bg.load(content);

        enemies = new ArrayList<EnemyShip>();
        for (int i = 0; i < 10; i++) {
            enemies.add(new EnemyShip(content.loadImage("gfx/enemy0.png")));
        }
        
        collMan = new CollisionManager();
    }

    public void update(float dt) {
        player.update(dt, bulletMan);
        player.clamp(0, 0, width, height);
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            e.update(dt, bulletMan);
        }
        bulletMan.update(dt);
        collMan.doCollision(player, enemies, bulletMan);
        bg.update(dt);
    }

    public void draw(Graphics g) {
        g.setColor(clearColor);
        g.fillRect(0, 0, width, height);
        bg.draw();
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            e.drawShadow();
        }
        for (int i = 0; i < enemies.size(); i++) {
            EnemyShip e = enemies.get(i);
            e.draw();
        }
        player.drawShadow();

        bulletMan.draw();
        player.draw();
    }
}
