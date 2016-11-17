/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myother.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 *
 * @author lemij7026
 */
public class SecondScreen implements Screen{
    Stage stage;
    Table table;
    BitmapFont font;
    Label label;
    Label.LabelStyle style;
    SpriteBatch sb;
    Game game;
    
    public SecondScreen(Game game){
        this.game = game;
       
    }

    @Override
    public void show() {

    stage = new Stage();
            table = new Table();
            table.setFillParent(true);
            table.center();
            font = new BitmapFont();
	style = new Label.LabelStyle(font, Color.WHITE);
        label = new Label("The Text For Second",style);
        stage.addActor(table);
    
    
    }

    @Override
    public void render(float f) {
        
         Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                stage.draw();
                table.reset();
                table.center();
                table.add(label).expandX().padTop(20);
                if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
                    game.setScreen(new MainScreen(game));
                }
                stage.act();
        
    }

    @Override
    public void resize(int i, int i1) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
      // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
