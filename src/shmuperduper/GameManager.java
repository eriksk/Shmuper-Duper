/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shmuperduper;

import Bullets.BulletManager;
import audio.AudioManager;
import collision.CollisionManager;
import content.ContentManager;
import maps.ScrollingBackground;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import particles.ParticleManager;
import particles.ParticleManagerImpl;
import ships.ShipManager;
import ships.ShipSpawner;

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
    private ShipSpawner spawner;
    
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
        
        collMan = new CollisionManager(this);
        shipMan = new ShipManager(width, height);
        shipMan.load(content);
        
        pMan = new ParticleManagerImpl(width, height);
        pMan.load(content);
        
        spawner = new ShipSpawner(width, height);
        
        AudioManager.I().load(content);        
        AudioManager.I().playSong("song1");
    }
    
    public void reset(){
        AudioManager.I().resetSong("song1");
        shipMan.player.score = 0;
        shipMan.reset();
        spawner.reset();
        pMan.reset();
        bulletMan.reset();
    }

    public void update(float dt) {
        spawner.update(dt, shipMan, content);
        shipMan.update(dt, bulletMan);
        bulletMan.update(dt);
        collMan.doCollision(shipMan.player, shipMan.enemies, bulletMan, pMan);
        
        pMan.update(dt, shipMan.player);
        bg.update(dt);
    }

    public void draw(Graphics g) {
        g.setColor(clearColor);
        g.fillRect(0, 0, width, height);
        
        bg.draw();
        shipMan.drawShadows();
        pMan.draw();
        shipMan.drawEnemies(g);
        bulletMan.drawPlayerBullets(g);
        shipMan.drawPlayers(g);
        bulletMan.drawEnemyBullets(g);
        
        // hud
        g.setColor(Color.white);
        g.drawString("Score: " + shipMan.player.score, 16, 16);
    }
}
