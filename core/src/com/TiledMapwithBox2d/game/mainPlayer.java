/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TiledMapwithBox2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class mainPlayer {
    
    RunGame run;
    Texture walkTexture;
    TextureRegion region;
    Sprite spr;
    int nCounter = 0;
    int nCounterX = 0;
    
    World world;
    Body body;
    BodyDef bodyDef;
    PolygonShape shape;
    FixtureDef fixDef;
    Fixture fix;
 
    
    public  mainPlayer(World mainWorld){
        
        run = new RunGame();
        walkTexture = new Texture(Gdx.files.internal("Walk\\0.png"));
        spr = new Sprite(walkTexture);
        world = mainWorld;
        bodyDef = new BodyDef();
        fixDef = new FixtureDef();
        bodyDef.type = BodyType.DynamicBody;
        
        bodyDef.position.set(spr.getX(), spr.getY());
        body = world.createBody(bodyDef);
        
        shape = new PolygonShape();
        shape.setAsBox(spr.getWidth()/2,spr.getHeight()/2);
        
        fixDef.shape = shape;
        fixDef.density = 1f;
        fix = body.createFixture(fixDef);
          
    }
    
    public void render(SpriteBatch sb){
        
       
        
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        
        spr.setPosition(body.getPosition().x, body.getPosition().y + 200);
        
        sb.draw(spr, spr.getX(), spr.getY());
        
        
        
        
    }
    
    
    
}
