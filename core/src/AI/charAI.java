/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import Tools.GameEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class charAI extends Sprite {
    
    World world;
    Vector2 posVec;
    Texture tex;
    SpriteBatch batch;
    public Body body;
    GameEngine GE;
    
    float ppm = 16;
    public charAI(World world,Vector2 positionVector,SpriteBatch batch,int width,int height){
        this.world = world;
        this.posVec = positionVector;
        this.batch = batch;
        setPosition(positionVector.x / ppm,positionVector.y / ppm);
        setSize(30 / ppm,30 / ppm);
        GE = new GameEngine(world);
        body = GE.createBody(world, positionVector, 60, 60,"player");
        
   
    }
    public void render(){
        this.setX(body.getPosition().x / ppm);
        this.setY(body.getPosition().y / ppm);
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            body.setLinearVelocity(10, 0);
        } else if(Gdx.input.isKeyPressed(Keys.LEFT)){
            body.setLinearVelocity(-10,0);
        } else if(Gdx.input.isKeyPressed(Keys.UP)){
            body.setLinearVelocity(0,10);
        }
        
    }
    //The translation
    

}
