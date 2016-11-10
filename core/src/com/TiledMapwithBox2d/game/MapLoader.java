/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.TiledMapwithBox2d.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import com.badlogic.gdx.physics.box2d.World;


/**
 *
 * @author lemij7026
 */
public class MapLoader  {
    
    
    TiledMap map;
    TiledMapRenderer mapRender;
    OrthographicCamera camera;
    MapObjects collisionObjects;
    BodyDef bDef;
    Body body;
    World world;
    FixtureDef fixDef;
    PolygonShape pgShape;
    
    Box2DDebugRenderer debugRenderer;
    Matrix4 debugMatrix;
    
    
    
  //Constructer
    public MapLoader(World worlMain){
            camera = new OrthographicCamera();
            camera.setToOrtho(false,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            debugMatrix = new Matrix4(camera.combined);
            camera.update();
            map = new TmxMapLoader().load("Maps\\level1.tmx");
            mapRender = new OrthogonalTiledMapRenderer(map,3.82f);
            collisionObjects = new MapObjects();
            world = worlMain;
            bDef = new BodyDef();
            fixDef = new FixtureDef();
            pgShape = new PolygonShape();
            debugRenderer = new Box2DDebugRenderer();
        
            
    }
      
    public void createBodies(){
        
        //collisionObjects = map.getLayers().get(1).getObjects();
        
        
        
        
        
         for(MapObject mapobMain: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
             Rectangle rectTemp = ((RectangleMapObject) mapobMain).getRectangle();
             bDef.type = BodyType.StaticBody;
             bDef.position.set(rectTemp.x + (rectTemp.width)/2, rectTemp.y + (rectTemp.height)/2);
             pgShape.setAsBox(rectTemp.getWidth() /2, rectTemp.getHeight() /2);
             body = world.createBody(bDef);
             fixDef.shape = pgShape;
             body.createFixture(fixDef);  
         }
         
         
         debugRenderer.render(world, debugMatrix);
         
//         for(int i = 0;i<collisionObjects.getCount();i++){
//             Rectangle rectObj = ((RectangleMapObject) collisionObjects.get(i)).getRectangle();
//             bDef.type = BodyType.StaticBody;
//             bDef.position.set(rectObj.x + (rectObj.width /2), rectObj.y +(rectObj.height /2));
//             body = world.createBody(bDef);
//             fixDef.shape = pgShape;
//             body.createFixture(fixDef);
//             
//         }
        
        
        
    }
   
    public void renderMap(){
        mapRender.setView(camera);
        mapRender.render();
    }

  
    
    
}
