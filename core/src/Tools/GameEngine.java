/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author lemij7026
 */
public class GameEngine {
    
    Body bTemp;
    Array bodies;
    Array staticVectors;
    Array aiVectors;
    World world;
    float ppm = 16;
    private short CATEGORY_AI = 8;
    public GameEngine(World world){
        this.world = world;
        
        bodies = new Array<Body>();
        
        staticVectors = new Array<Vector2>();
        
        aiVectors = new Array<Vector2>();
        
    }
    
    
    //When looping through the bodies append to array list here
    //Only the static bodies
    //No need for world.getbodies anymore
    public void loadLayer(int nLayer,TiledMap map){
        BodyDef bodDef = new BodyDef();
       // MapObjects objs = map.getLayers().get(nLayer).getObjects();
         for(MapObject mapobMain: map.getLayers().get(nLayer).getObjects().getByType(RectangleMapObject.class)) {
             bodDef.type = BodyType.StaticBody;
             Rectangle rectObj = ((RectangleMapObject) mapobMain).getRectangle();
             bodDef.position.set((rectObj.getX() + rectObj.getWidth()/2)/ppm, (rectObj.getY() + rectObj.getHeight()/2)/ppm);
             Body body = world.createBody(bodDef);
             PolygonShape ps = new PolygonShape();
             ps.setAsBox(rectObj.getWidth()/2 / ppm, rectObj.getHeight()/2 / ppm);
             FixtureDef fixDef = new FixtureDef();
             fixDef.shape = ps;
             fixDef.density = 1f;
             fixDef.friction = 1f;
             Fixture fix = body.createFixture(fixDef);
             
             bodies.add(body);
         }
         
         
    }
    
    public Body createBody(World world,Vector2 positionVec, int width,int height,String sUserType){
        BodyDef bodDef = new BodyDef();
        bodDef.position.set(positionVec);
        bodDef.type = BodyType.DynamicBody;
        bodDef.fixedRotation = true;
        bTemp = world.createBody(bodDef);
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width/2 / ppm, height/2 / ppm);
        FixtureDef fixDef = new FixtureDef();
        fixDef.density = 1f;
        fixDef.shape = ps;
        Fixture fix = bTemp.createFixture(fixDef);
        bTemp.setUserData(sUserType);
        return bTemp;
    }
    
    
   
            
     public void loadMapLayer(int nLayer, World wTemp, TiledMap tmTemp) {
        for(MapObject mObj: tmTemp.getLayers().get(nLayer).getObjects()) {
            FixtureDef fdTemp = null;
            BodyDef bdTemp = new BodyDef();
            bdTemp.type = BodyDef.BodyType.StaticBody;
            if(mObj instanceof PolylineMapObject) {
                fdTemp = createPolylineFixture((PolylineMapObject) mObj);
            } else if(mObj instanceof RectangleMapObject) {
                fdTemp = createRectangleFixture((RectangleMapObject) mObj);
                bdTemp.position.set(getRectangleCoordinates((RectangleMapObject) mObj));
            } else if(mObj instanceof PolygonMapObject) {
                fdTemp = createPolygonFixture((PolygonMapObject) mObj);
            }
            Body bTemp = wTemp.createBody(bdTemp);
            bTemp.createFixture(fdTemp);
        }
    }
     
      private FixtureDef createPolylineFixture(PolylineMapObject pmObj) {
        FixtureDef fdTemp = new FixtureDef();
        float[] fCoordinates = pmObj.getPolyline().getTransformedVertices();
        Vector2[] vecPoints = new Vector2[fCoordinates.length / 2];
        for(int i = 0; i < vecPoints.length; i++) {
            vecPoints[i] = new Vector2();
            vecPoints[i].x = fCoordinates[i * 2] / ppm;
            vecPoints[i].y = fCoordinates[i * 2 + 1] / ppm;
        }
        ChainShape csTemp = new ChainShape();
        csTemp.createChain(vecPoints);
        fdTemp.shape = csTemp;
        fdTemp.friction = 0.5f;
        return fdTemp;
    }

    private FixtureDef createRectangleFixture(RectangleMapObject rmObj) {
        Rectangle rectTemp = rmObj.getRectangle();
        FixtureDef fdTemp = new FixtureDef();
        PolygonShape psTemp = new PolygonShape();
        psTemp.setAsBox(rectTemp.width / 2 /ppm , rectTemp.height / 2 /ppm );
        fdTemp.shape = psTemp;
        return fdTemp;
     
    }
    
    private FixtureDef createPolygonFixture(PolygonMapObject pmObj) {
        float[] fVerticies = pmObj.getPolygon().getTransformedVertices();
        Vector2[] vecPoints = new Vector2[fVerticies.length / 2];
        for(int i = 0; i < fVerticies.length / 2; i++) {
            vecPoints[i] = new Vector2();
            vecPoints[i].x = fVerticies[i * 2] / ppm;
            vecPoints[i].y = fVerticies[i * 2 + 1] / ppm;
        }
        PolygonShape psTemp = new PolygonShape();
        psTemp.set(vecPoints);
        FixtureDef fdTemp = new FixtureDef();
        fdTemp.shape = psTemp;
        return fdTemp;
    }
    
    
     private Vector2 getRectangleCoordinates(RectangleMapObject rmObj) {
        Rectangle rectTemp = rmObj.getRectangle();
        Vector2 vecLocation = new Vector2((rectTemp.getX() + rectTemp.width / 2) / ppm, (rectTemp.getY() + rectTemp.height / 2) / ppm);
        return vecLocation;
    }

     
     //Get all body location vectors, populate vector array list
     
     

     public Array worldVectors(){
         world.getBodies(bodies);
         
         for(Object obj : bodies){
             Body bTemp = (Body)obj;
             if(bTemp.getUserData() == "AI"){
                 aiVectors.add(bTemp.getPosition());
             }
             
         }
         
         
         
         return aiVectors;
     }
}
