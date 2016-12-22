package comtiled;

import Player.charPlayer;
import Tools.GameEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
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
	
        
        charPlayer player;
        Vector2 playerVec;
        Texture img;
        
        //LoadLayers
        GameEngine GE;
	@Override
	public void create () {
		batch = new SpriteBatch();
                GE = new GameEngine();
                world = new World(new Vector2(0,-98f),true);
                boxRender = new Box2DDebugRenderer();
		img = new Texture("badlogic.jpg");
                playerVec = new Vector2(100,100);
                cam = new OrthographicCamera();
                tiledmap = new TmxMapLoader().load("Map2/map1.tmx");
                tiledRender = new OrthogonalTiledMapRenderer(tiledmap);
                viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),cam);
                viewport.apply();
                cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 2);
                GE.loadLayer(4, world, tiledmap);
                GE.loadLayer(5, world, tiledmap);
                GE.loadLayer(6, world, tiledmap);
                player = new charPlayer(world,playerVec,img,batch,img.getWidth(),img.getHeight());
                
                
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
                world.step(Gdx.graphics.getDeltaTime(), 4, 6);
                cam.update();
                tiledRender.render();
                tiledRender.setView(cam);
                boxRender.render(world, cam.combined);
                player.render();
		batch.end();
	}
 
        @Override
   public void resize(int width, int height){
      viewport.update(width,height);
      cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
   }
}
