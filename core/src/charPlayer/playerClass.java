/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package charPlayer;

import Tools.GameEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import comtiled.main;


public class playerClass extends Sprite {
    
    World world;
    Vector2 posVec;
    Texture tex;
    SpriteBatch batch;
    public Body body;
    GameEngine GE;
    main main;
    
    public playerClass(World world,Vector2 positionVector,SpriteBatch batch,int width,int height){
        main = new main();
        this.world = world;
        this.posVec = positionVector;
        this.batch = batch;
        setPosition(posVec.x / main.PIXELS_TO_METERS,posVec.y / main.PIXELS_TO_METERS);
        setSize(width / main.PIXELS_TO_METERS,height / main.PIXELS_TO_METERS);
        GE = new GameEngine(world);
        body = GE.createBody(world, positionVector, 60, 60,"player");
        
   
    }
    public void render(){
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            body.setLinearVelocity(10, 0);
        } else if(Gdx.input.isKeyPressed(Keys.LEFT)){
            body.setLinearVelocity(-10,0);
        } else if(Gdx.input.isKeyPressed(Keys.UP)){
            body.setLinearVelocity(0,10);
        }
        
    }
    

}
