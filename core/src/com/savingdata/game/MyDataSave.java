package com.savingdata.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDataSave extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
        Json json;
        JsonValue value;
        JsonReader jsonRead;
        FileHandle file;
        float nCounter = 0f;
        
        //bar
        ProgressBar pb;
        ProgressBar.ProgressBarStyle barStyle;
        Skin skin;
        TextureAtlas atlas;
        Texture tex;
        Person p;
	
	@Override
	public void create () {
            p = new Person();
            tex = new Texture(Gdx.files.internal("progressbartexture.png"));
            
            atlas = new TextureAtlas(Gdx.files.internal("ProgressSkins/Progress.pack"));
            skin = new Skin(atlas);
	    batch = new SpriteBatch();
            barStyle = new ProgressBar.ProgressBarStyle(skin.getDrawable("progressbartexture"), skin.getDrawable("knob"));
            pb = new ProgressBar(0,1000,0.5f,false,barStyle);
           
            p.sName = Gdx.app.toString();
            p.nAge = 5;
            
            file = new FileHandle("The Json File");
            json = new Json();
            json.toJson(p, file);
            
            
            
	  
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
                nCounter += 1f;
                pb.setValue(nCounter);
                System.out.println(pb.getPercent());
		pb.draw(batch, 0.5f);
		batch.end();
	}
}
