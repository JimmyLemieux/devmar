package com.myother.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class OtherMy extends Game {
	SpriteBatch batch;
	
        Stage stage;
        Table table;
        Label label;
        Label.LabelStyle style;
        BitmapFont font;
        
        Texture[] tex;
        TextureRegion region;
        TextureAtlas atlas;
        Animation texAnimation;
        float nCounter = 0;
        int nCounterX = 0;
        Vector2 vec;
        
	@Override
	public void create () {

           batch = new SpriteBatch();
           atlas = new TextureAtlas("run/megapack.pack");
           texAnimation = new Animation(1/9f,atlas.getRegions());
          
           vec = new Vector2(50,50);
           //setScreen(new MainScreen(this));
           
           
            
		
	}

	@Override
	public void render () {
            super.render();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
                
                
                if(Gdx.input.isKeyPressed(Keys.LEFT)){
                    vec.x -= 5;
                } else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
                    vec.x += 5;
                }
                batch.draw(texAnimation.getKeyFrame(nCounter += Gdx.graphics.getDeltaTime(),true), vec.x, vec.y);
		batch.end();
	}
}
