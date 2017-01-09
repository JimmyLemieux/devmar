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
    public charAI(World world,Vector2 positionVector,SpriteBatch batch,int width,int height){
        this.world = world;
        this.posVec = positionVector;
        this.batch = batch;
        setPosition(positionVector.x,positionVector.y);
        this.scale(1f);
        this.setSize(60, 60);
        GE = new GameEngine(world);
        body = GE.createBody(world, positionVector, 60, 60);
        worldBodies = GE.worldBodies();
        vectors = GE.worldVectors();
    
        index = 2;
       
        
        
    }
    
    public void render(){
        this.setX(body.getPosition().x);
        this.setY(body.getPosition().y);
       
        
        wanderFunc(vectors,index);
        
         
   
    }
   
    
    public Vector2 moveAi(Vector2 destVec){
        Vector2 tempPlayerVec = new Vector2(body.getPosition());
        Vector2 tempDestVec = new Vector2(destVec);
        tempDestVec.sub(tempPlayerVec).nor();
        tempPlayerVec.x += tempDestVec.x;
        //Avoid weird jumping
        tempPlayerVec.y += tempDestVec.y;
        return tempPlayerVec;
    }
    
    public void wanderFunc(Array vecs,int index){
        
        boolean isDoubleJump = false;
       
        Vector2 tempDestVec = new Vector2((Vector2) vectors.get(index));

        //Moving the AI
        body.setTransform(moveAi(tempDestVec), 0);
            
        
        //TODO: Clean this up and make more efficient
            
            //Jump only when the body hits the ground 
                if(body.getLinearVelocity().y == 0f){
                    //When the body is above the y stop jumping
                    if(body.getPosition().y < tempDestVec.y){
                        //lol scaling 
                   body.applyLinearImpulse(new Vector2(0,1000000000), body.getPosition(), true);
                   }
                }

                
             
                
                
                //If the body is trapped under another object
                if(Math.abs(body.getPosition().x - tempDestVec.x) < 50){
                    System.out.println("Needs to move");
                    System.out.println(body.getPosition().x - tempDestVec.x);
                    body.applyLinearImpulse(new Vector2(10000000,0), body.getPosition(), true);
                }
                
                
                
            if(body.getPosition().dst(tempDestVec) <= 50){
                System.out.println("The body is here!");
            }
       
        
        
    }
    
    
    
    
}
