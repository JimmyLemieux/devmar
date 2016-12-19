/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author lemij7026
 */
public class GameEngine {
    
    public GameEngine(){
        
    }
    
    
    public void loadLayer(int nLayer,World world,TiledMap map){
        BodyDef bodDef = new BodyDef();
       // MapObjects objs = map.getLayers().get(nLayer).getObjects();
         for(MapObject mObj: map.getLayers().get(nLayer).getObjects()) {
             
             bodDef.type = BodyType.StaticBody;
             Rectangle rectObj = ((RectangleMapObject) mObj).getRectangle();
             bodDef.position.set(rectObj.getX() + rectObj.getWidth()/2, rectObj.getY() + rectObj.getHeight()/2);
             Body body = world.createBody(bodDef);
             PolygonShape ps = new PolygonShape();
             
             ps.setAsBox(rectObj.getWidth()/2, rectObj.getHeight()/2);
             
             
             
             
             
             
         }
        
        
        
    }
            
    
    
    
    
}
