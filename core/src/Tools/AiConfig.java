/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import charPlayer.playerClass;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import comtiled.main;

/**
 *
 * @author lemij7026
 */
//TODO: Add textures to all the bodies as well
public final class AiConfig  {

    World world;
    SpriteBatch batch;
    GameEngine GE;
    Array aiBodies = new Array<Object>();
    Array spawnLocation = new Array<Object>();
    Body body;
    Body tempAiBod;
    Texture tex;
    playerClass ai;
    main main;
    Vector2 tempAiVec;
    Vector2 tempAiStartVec;

    public AiConfig(World world, SpriteBatch batch) {
        this.world = world;
        this.batch = batch;
        GE = new GameEngine(world);
        main = new main();

    }

    public void makeAI(int nNumber, Texture tex, int width, int height) {
        //Instead set spawn locations
        for (int i = 0; i < nNumber; i++) {
            int nRandom = (int) (Math.random() * 1000 + 50);
            Vector2 bodVec = new Vector2(nRandom / main.PIXELS_TO_METERS, nRandom / main.PIXELS_TO_METERS / 1.5f);
            spawnLocation.add(bodVec);
            //Then have these spawn at body vectors 
            body = GE.createBody(world, bodVec, width, height, "AI");
            aiBodies.add(body);

        }

    }

    //I send the individual body along with vector position. To avoid lag
    //We need to make new instances of each vector since they are constantly being manipulated
    public void moveAi(Vector2 destVec, Body tempAiBod, Vector2 aiBodyVec) {
        //Instead of looping through all, just have body moving which is closest to player
        Vector2 tempAiVec = new Vector2(aiBodyVec);
        Vector2 tempDestVec = new Vector2(destVec);
        tempDestVec.sub(tempAiVec).nor();
        tempAiVec.x += tempDestVec.x / main.PIXELS_TO_METERS;
        tempAiVec.y += tempDestVec.y / main.PIXELS_TO_METERS * 5;
        tempAiBod.setTransform(tempAiVec, 0);
    }

    public void update(Vector2 playerVec) {
        for (Object obj : aiBodies) {
            Body bTemp = (Body) obj;
            bTemp.setAwake(true);
        }
        if (isClose(playerVec)) {
            this.moveAi(playerVec, tempAiBod, tempAiVec);
        } else {
            //Send in ai body and its position
            //Have them teleport back instead
            //I have to find efficeient way
            System.out.println("Far away");
        }

    }

    //To have more than one Ai moving at the same time, Add the active bodies to array list
    public boolean isClose(Vector2 playerVec) {
        for (Object obj : aiBodies) {
            Body bTemp = (Body) obj;
            if (bTemp.getPosition().dst(playerVec) < 20) {
                System.out.println(obj);
                tempAiBod = bTemp;
                tempAiVec = new Vector2(bTemp.getPosition());
                return true;
            } 
        }

        return false;
    }

}
