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
    Array worldBodies;
    Array vectors;
    int index;
    GameEngine GE;
    
    float ppm = 16;
    public charAI(World world,Vector2 positionVector,SpriteBatch batch,int width,int height){
        this.world = world;
        this.posVec = positionVector;
        this.batch = batch;
        setPosition(positionVector.x / ppm,positionVector.y / ppm);
        setSize(30 / ppm,30 / ppm);
        GE = new GameEngine(world);
        body = GE.createBody(world, positionVector, 60, 60);
        
        worldBodies = GE.worldBodies();
        vectors = GE.worldVectors();
        index = 2;
    }
    public void render(){
        this.setX(body.getPosition().x / ppm);
        this.setY(body.getPosition().y / ppm);
        wanderFunc(vectors,index);
    }
    //The translation
    public Vector2 moveAi(Vector2 destVec){
        Vector2 tempPlayerVec = new Vector2(body.getPosition().x, body.getPosition().y);
        Vector2 tempDestVec = new Vector2(destVec);
        tempDestVec.sub(tempPlayerVec).nor();
        tempPlayerVec.x += tempDestVec.x / ppm;
        tempPlayerVec.y += tempDestVec.y / ppm;
        return tempPlayerVec;
    }

    public void wanderFunc(Array vecs,int index){
        Vector2 tempDestVec = new Vector2((Vector2) vectors.get(index));
        //Moving the AI
        body.setTransform(moveAi(tempDestVec), 0);
        //TODO: Clean this up and make more efficient
           

                if(body.getLinearVelocity().y == 0f){
                    //When the body is above the y stop jumping
                   if(body.getPosition().y < tempDestVec.y){
                   body.applyLinearImpulse(new Vector2(0,100), body.getPosition(), true);
                   }
                }

 
            
       
        
        
    }
    
    
    
    
}
