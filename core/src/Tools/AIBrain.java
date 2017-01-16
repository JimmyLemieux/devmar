/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

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
    Array worldBodies = new Array<Vector2>();
    Body body;
    Body bTemp;
    
    float ppm = 16;
    public AIBrain(World world, Array worldVectors){
        this.world = world;
        GE = new GameEngine(world);
        //null
       
        
        
    }
    
    
    
    public void makeAI(int nNumber,int width, int height){
        for(int i = 0;i<nNumber;i++){
            //Then have these spawn at body vectors 
           body = GE.createBody(world, new Vector2(200/ppm,200/ppm), width, height);
            aiBodies.add(body);
        }
       
        
        
    }
    
  
    
   public void moveAi(Vector2 destVec){
       
       //Instead of looping through all, just have body moving which is closest to player
   for(Object obj : aiBodies){
        Body bTemp = (Body)obj;
        Vector2 tempPlayerVec = new Vector2(body.getPosition().x, body.getPosition().y);
        Vector2 tempDestVec = new Vector2(destVec);
        tempDestVec.sub(tempPlayerVec).nor();
        tempPlayerVec.x += tempDestVec.x / ppm;
        tempPlayerVec.y += tempDestVec.y / ppm;
       bTemp.setTransform(tempPlayerVec, 0);
        
         }
         
    }
    
      public void update() {
        
          //Instead move to player Vec
       moveAi(new Vector2(500/ppm,500/ppm));
           
        }
        
    }
      
      
     
    
    

