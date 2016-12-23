/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Tools.GameEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;


public class charPlayer extends Sprite {
    
    World world;
    Vector2 posVec;
    Texture tex;
    SpriteBatch batch;
    Body body;
    Array vecList;
    
    GameEngine GE;
    public charPlayer(World world,Vector2 positionVector,Texture tex,SpriteBatch batch,int width,int height){
        this.world = world;
        this.posVec = positionVector;
        this.tex = tex;
        this.batch = batch;
        this.setRegion(tex);
        setPosition(positionVector.x,positionVector.y);
        this.scale(1f);
        this.setSize(60, 60);
        GE = new GameEngine();
        body = GE.createBody(world, positionVector, 60, 60);
        //The vector arraylist
        vecList = new Array();
        vecList.add(new Vector2(423,378));
        vecList.add(new Vector2(665,372));
        vecList.add(new Vector2(809,384));
    }
    
    public void render(){
        this.setX(body.getPosition().x);
        this.setY(body.getPosition().y);
        
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
            body.setLinearVelocity(new Vector2(1000,0));   
        } else if(Gdx.input.isKeyPressed(Keys.LEFT)){
            body.setLinearVelocity(new Vector2(-1000,0));
        } else if(Gdx.input.isKeyPressed(Keys.UP)){
            body.applyLinearImpulse(new Vector2(1000,0), posVec, true);
        }
        
        body.setLinearVelocity(new Vector2(50,0));
        batch.draw(this, getX(), getY());
    }
    
    public Vector2 pathFind(Vector2 destVec){
        Vector2 tempPlayerVec = new Vector2(posVec);
        Vector2 tempDestVec = new Vector2(destVec);
        tempDestVec.sub(tempPlayerVec).nor();
        tempPlayerVec.x += tempDestVec.x;
        tempPlayerVec.y += tempDestVec.y;
        return tempPlayerVec;
         
    }
    
    
}
