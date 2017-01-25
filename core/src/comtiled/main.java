package comtiled;

import charPlayer.playerClass;
import Tools.AiConfig;
import Tools.GameEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class main extends ApplicationAdapter {

    SpriteBatch batch;
    World world;
    TiledMap tiledmap;
    TiledMapRenderer tiledRender;
    Viewport viewport;
    OrthographicCamera cam;
    //Box2d debugRender
    Box2DDebugRenderer boxRender;
    playerClass player;
    Vector2 playerVec;
    //LoadLayers
    GameEngine GE;
    AiConfig ai;
    //This is the scaling constant, the tiled map ratio
    //Convert the pixels to meters 
    //http://gamedev.stackexchange.com/questions/87917/box2d-meters-and-pixels
    public float PIXELS_TO_METERS = 16;
    @Override
    public void create() {
        //init
        batch = new SpriteBatch();
        world = new World(new Vector2(0, -9.8f), true);
        GE = new GameEngine(world);
        boxRender = new Box2DDebugRenderer();
        playerVec = new Vector2(100 / PIXELS_TO_METERS, 100 / PIXELS_TO_METERS);
        //Views
        cam = new OrthographicCamera();
        tiledmap = new TmxMapLoader().load("Map2/map1.tmx");
        tiledRender = new OrthogonalTiledMapRenderer(tiledmap, 1 / PIXELS_TO_METERS);
        viewport = new FitViewport(Gdx.graphics.getWidth() / PIXELS_TO_METERS, Gdx.graphics.getHeight() / PIXELS_TO_METERS, cam);
        viewport.apply();
        //Load Layers on tiled map
        GE.loadLayer(4, tiledmap);
        GE.loadLayer(5, tiledmap);
        GE.loadLayer(6, tiledmap);
        //Config AI
        ai = new AiConfig(world, batch);
        player = new playerClass(world, playerVec, batch, 50, 50);   
    }
    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 206, 209, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        world.step(Gdx.graphics.getDeltaTime(), 4, 6);
        cam.position.set(new Vector3(player.body.getPosition().x,player.body.getPosition().y,4));
        cam.update();
        tiledRender.render();
        tiledRender.setView(cam);
        boxRender.render(world, cam.combined);
        ai.update(player.body.getPosition());
        player.render();
        if(Gdx.input.isKeyJustPressed(Keys.N)){
            ai.makeAI(1, new Texture(Gdx.files.internal("simple.png")), 25, 25);
        }
        batch.end();
    }
    //
    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = width / PIXELS_TO_METERS;
        cam.viewportHeight = height / PIXELS_TO_METERS;
        cam.position.set(new Vector3(width / 2 / PIXELS_TO_METERS, height / 2 / PIXELS_TO_METERS, 0));
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width / PIXELS_TO_METERS, height / PIXELS_TO_METERS);
        cam.update();
    }
}
