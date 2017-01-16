/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import AI.charAI;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author lemij7026
 */
public final class AIBrain {
    World world;
    GameEngine GE;
    Array aiBodies = new Array<Body>();
    Body body;
    Body bTemp;
    
    float ppm = 16;
    
    charAI ai;
    public AIBrain(World world){
        this.world = world;
        GE = new GameEngine(world);
        //null
       
        
        
    }
    
    
    
    public void makeAI(int nNumber,Array worldVectors,int width, int height){
        for(int i = 0;i<nNumber;i++){
            int nRandom = (int)(Math.random() * 1000 + 50);
           
            //Then have these spawn at body vectors 
           body = GE.createBody(world, new Vector2(nRandom / ppm , nRandom / ppm ), width , height);
            aiBodies.add(body);
        }
       
        
        
    }
    
  
    
   public void moveAi(Vector2 destVec){
       
       //Instead of looping through all, just have body moving which is closest to player
        Vector2 tempPlayerVec = new Vector2(body.getPosition().x, body.getPosition().y);
        Vector2 tempDestVec = new Vector2(destVec);
        tempDestVec.sub(tempPlayerVec).nor();
        tempPlayerVec.x += tempDestVec.x / ppm;
        tempPlayerVec.y += tempDestVec.y / ppm;
        bTemp.setTransform(tempPlayerVec, 0);
        
         
         
    }
   
   
   
  
    
      public void update(Vector2 playerVec) {
        
          //Instead move to player Vec
           System.out.println(playerVec);
        }
        
    }
      
      
     
    
    

