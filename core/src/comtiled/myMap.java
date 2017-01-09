package comtiled;

import AI.charAI;
import Tools.GameEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class myMap extends ApplicationAdapter {
	SpriteBatch batch;
	
        
        TiledMap tiledmap;
        TiledMapRenderer tiledRender;
        
        Viewport viewport;
        OrthographicCamera cam;
        
        
        //Box2d debugRender
        Box2DDebugRenderer boxRender;
        World world;
	
        
        charAI player;
        Vector2 playerVec;
        
        //LoadLayers
        GameEngine GE;
  
        //Bodies in the world
        Array bodies;
        float ppm = 16;
        
        
	@Override
	public void create () {
            //init
		batch = new SpriteBatch();
                world = new World(new Vector2(0,-98f),true);
                GE = new GameEngine(world);
                boxRender = new Box2DDebugRenderer();
                playerVec = new Vector2(100 / ppm,100 / ppm);
                //Views
                cam = new OrthographicCamera();
                tiledmap = new TmxMapLoader().load("Map2/map1.tmx");
                tiledRender = new OrthogonalTiledMapRenderer(tiledmap,1 / ppm);
                viewport = new FitViewport(Gdx.graphics.getWidth() / ppm,Gdx.graphics.getHeight() / ppm,cam);
                viewport.apply();
                
                //CALLING
                GE.loadLayer(4, tiledmap);
                GE.loadLayer(5, tiledmap);
                GE.loadLayer(6, tiledmap);
                player = new charAI(world,playerVec,batch,50,50);

	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(cam.combined);
		batch.begin();
                
                world.step(Gdx.graphics.getDeltaTime(), 4, 6);
                
                cam.update();
                tiledRender.render();
                tiledRender.setView(cam);
                boxRender.render(world, cam.combined);
                
                
                if(Gdx.input.isKeyPressed(Keys.RIGHT)){
                    cam.translate(5, 0);
                } else if(Gdx.input.isKeyPressed(Keys.LEFT)){
                    cam.translate(-5, 0);
                }
                
                player.render();
		batch.end();
	}
 
        @Override
   public void resize(int width, int height){
      cam.viewportWidth = width / ppm;
      cam.viewportHeight = height / ppm;
      cam.position.set(new Vector3(width / 2 / ppm, height / 2 / ppm, 0));
      cam.update();
   }
   

   
   
   
}
