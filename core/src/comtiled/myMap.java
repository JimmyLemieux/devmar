package comtiled;

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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class myMap extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        
        TiledMap tiledmap;
        TiledMapRenderer tiledRender;
        
        Viewport viewport;
        OrthographicCamera cam;
        
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
                cam = new OrthographicCamera();
                tiledmap = new TmxMapLoader().load("Map2/map1.tmx");
                tiledRender = new OrthogonalTiledMapRenderer(tiledmap);
                viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),cam);
                viewport.apply();
                cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 0);
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
                batch.setProjectionMatrix(cam.combined);
                tiledRender.render();
                tiledRender.setView(cam);
		batch.end();
	}
 
        @Override
   public void resize(int width, int height){
      viewport.update(width,height);
      cam.position.set(cam.viewportWidth/2,cam.viewportHeight/2,0);
   }
}
