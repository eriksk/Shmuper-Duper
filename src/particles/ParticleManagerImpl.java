/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package particles;

import content.ContentManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import utilities.ListUtils;
import utilities.Pool;
import utilities.Util;

/**
 *
 * @author Erik
 */
public class ParticleManagerImpl extends ParticleManager{

    private Image texture;
    private SpriteSheet sheet;
    
    public ParticleManagerImpl(int width, int height) {
        super(width, height);
    }

    @Override
    public void load(ContentManager content) {
        super.load(content);
        particles = new Pool(1024);
        particles.init(ListUtils.allocate(particles.getCapacity(), Particle.class));
        
        texture = content.loadImage("gfx/particles.png");
        sheet = new SpriteSheet(texture, 16, 16);
    }
    
    public void explode(float x, float y){       
        for (int i = 0; i < 128; i++) {
            Particle p = (Particle)particles.pop();
            p.current = 0f;
            p.duration = Util.Rand(400, 1000);
            p.x = x;
            p.y = y;
            p.vx = Util.Rand(-1f, 1f) * 0.1f;
            p.vy = Util.Rand(-1f, 1f) * 0.1f;
            p.x += p.vx * Util.Rand(32f, 128f);
            p.y += p.vy * Util.Rand(32f, 128f);
            p.srcCol = 0;
            p.srcRow = 0;
        }
    }

    @Override
    public void update(float dt) {
        for (int i = 0; i < particles.getCount(); i++) {
            Particle p = (Particle)particles.get(i);
            p.current += dt;
            if(p.current > p.duration){
                particles.push(i--);
            }else{
                p.x += p.vx * dt;
                p.y += p.vy * dt;
            }
        }
    }

    @Override
    public void draw() {        
        texture.startUse();
        for (int i = 0; i < particles.getCount(); i++) {
            Particle p = (Particle)particles.get(i);
            sheet.renderInUse((int)p.x, (int)p.y, p.srcCol, p.srcRow);
        }
        texture.endUse();
    }
}
