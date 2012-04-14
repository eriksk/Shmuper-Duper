/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shmuperduper;

import Bullets.BulletManager;
import collision.CollisionManager;
import content.ContentManager;
import maps.ScrollingBackground;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import particles.ParticleManager;
import particles.ParticleManagerImpl;
import ships.ShipManager;

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
    private BulletManager bulletMan;
    private CollisionManager collMan;
    private ShipManager shipMan;
    private ParticleManagerImpl pMan;
    
    public GameManager(int width, int height) {
        this.width = width;
        this.height = height;
        clearColor = new Color(163, 139, 73);
    }

    public void load(GameContainer container) {
        content = new ContentManager("resources");

        bulletMan = new BulletManager();
        bulletMan.load(content);

        bg = new ScrollingBackground(width, height);
        bg.load(content);
        
        collMan = new CollisionManager();
        shipMan = new ShipManager(width, height);
        shipMan.load(content);
        
        pMan = new ParticleManagerImpl(width, height);
        pMan.load(content);
    }

    public void update(float dt) {
        shipMan.update(dt, bulletMan);
        bulletMan.update(dt);
        collMan.doCollision(shipMan.player, shipMan.enemies, bulletMan, pMan);
        
        pMan.update(dt);
        bg.update(dt);
    }

    public void draw(Graphics g) {
        g.setColor(clearColor);
        g.fillRect(0, 0, width, height);
        
        bg.draw();
        shipMan.drawEnemies(g);
        bulletMan.drawPlayerBullets(g);
        shipMan.drawPlayers(g);
        bulletMan.drawEnemyBullets(g);
        
        pMan.draw();
    }
}
