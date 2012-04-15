/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particles;

import audio.AudioManager;
import collision.CollisionManager;
import content.ContentManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import ships.PlayerShip;
import utilities.ListUtils;
import utilities.Pool;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ParticleManagerImpl extends ParticleManager {

    private Image texture;
    private SpriteSheet sheet;

    public ParticleManagerImpl(int width, int height) {
        super(width, height);
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);
        particles = new Pool(2048);
        particles.init(ListUtils.allocate(particles.getCapacity(), Particle.class));

        texture = content.loadImage("gfx/particles.png");
        sheet = new SpriteSheet(texture, 16, 16);
    }
    
    public void reset(){
        particles.clear();
    }

    public void spawn(float x, float y) {
            Particle p = (Particle) particles.pop();
            p.current = 0f;
            p.duration = Util.Rand(400, 1000);
            p.x = x + Util.Rand(-64, 64);
            p.y = y + Util.Rand(-64, 64);
            p.vx = 0f;
            p.vy = -1f;
            p.srcCol = 0;
            p.srcRow = 0;
    }
    public void explode(float x, float y) {
        for (int i = 0; i < 64; i++) {
            Particle p = (Particle) particles.pop();
            p.current = 0f;
            p.duration = Util.Rand(400, 1000);
            p.x = x + Util.Rand(-64, 64);
            p.y = y + Util.Rand(-64, 64);
            p.vx = Util.Rand(-1f, 1f) * 0.8f;
            p.vy = Util.Rand(-1f, 1f) * 0.8f;
            p.srcCol = 0;
            p.srcRow = 0;
        }
    }

    public void update(float dt, PlayerShip player) {
        float decel = 0.001f;
        for (int i = 0; i < particles.getCount(); i++) {
            Particle p = (Particle) particles.get(i);
            if (p.vx == 0f && p.vy == 0f) {
                p.x = Util.Lerp(p.x, player.x + player.hitbox.width / 2f, 0.001f * dt);
                p.y = Util.Lerp(p.y, player.y + player.hitbox.height / 2f, 0.001f * dt);
                if (CollisionManager.intersects(p, player)) {
                    AudioManager.I().playSound("coin");
                    player.score += 10;
                    particles.push(i--);
                }
            }else{
                p.x += p.vx * dt;
                p.y += p.vy * dt;
                if(p.vx > 0f){
                    p.vx -= decel * dt;
                    if(p.vx < 0f){
                        p.vx = 0f;
                    }
                }
                if(p.vx < 0f){
                    p.vx += decel * dt;
                    if(p.vx > 0f){
                        p.vx = 0f;
                    }
                }
                if(p.vy > 0f){
                    p.vy -= decel * dt;
                    if(p.vy < 0f){
                        p.vy = 0f;
                    }
                }
                if(p.vy < 0f){
                    p.vy += decel * dt;
                    if(p.vy > 0f){
                        p.vy = 0f;
                    }
                }
            }
        }
    }

    @Override
    public void draw() {
        texture.startUse();
        for (int i = 0; i < particles.getCount(); i++) {
            Particle p = (Particle) particles.get(i);
            sheet.renderInUse((int) p.x, (int) p.y, p.srcCol, p.srcRow);
        }
        texture.endUse();
    }
}
