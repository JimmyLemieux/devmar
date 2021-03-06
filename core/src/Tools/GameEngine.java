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
import comtiled.main;

/**
 *
 * @author lemij7026
 */
public class GameEngine {

    Body bTemp;
    World world;
    main main;
    public GameEngine(World world) {
        this.world = world;
        main = new main();

    }

    public void loadLayer(int nLayer, TiledMap map) {
        BodyDef bodDef = new BodyDef();
        for (MapObject mapobMain : map.getLayers().get(nLayer).getObjects().getByType(RectangleMapObject.class)) {
            bodDef.type = BodyType.StaticBody;
            Rectangle rectObj = ((RectangleMapObject) mapobMain).getRectangle();
            bodDef.position.set((rectObj.getX() + rectObj.getWidth() / 2) / main.PIXELS_TO_METERS, (rectObj.getY() + rectObj.getHeight() / 2) / main.PIXELS_TO_METERS);
            Body body = world.createBody(bodDef);
            PolygonShape ps = new PolygonShape();
            ps.setAsBox(rectObj.getWidth() / 2 / main.PIXELS_TO_METERS, rectObj.getHeight() / 2 / main.PIXELS_TO_METERS);
            FixtureDef fixDef = new FixtureDef();
            fixDef.shape = ps;
            fixDef.density = 1f;
            fixDef.friction = 1f;
            Fixture fix = body.createFixture(fixDef);

        }

    }

    public Body createBody(World world, Vector2 positionVec, int width, int height, String sUserType) {
        BodyDef bodDef = new BodyDef();
        bodDef.position.set(positionVec);
        bodDef.type = BodyType.DynamicBody;
        bodDef.fixedRotation = true;
        bTemp = world.createBody(bodDef);
        PolygonShape ps = new PolygonShape();
        ps.setAsBox(width / 2 / main.PIXELS_TO_METERS, height / 2 / main.PIXELS_TO_METERS);
        FixtureDef fixDef = new FixtureDef();
        fixDef.density = 1f;
        fixDef.shape = ps;
        Fixture fix = bTemp.createFixture(fixDef);
        bTemp.setUserData(sUserType);
        return bTemp;
    }

    public void loadMapLayer(int nLayer, World wTemp, TiledMap tmTemp) {
        for (MapObject mObj : tmTemp.getLayers().get(nLayer).getObjects()) {
            FixtureDef fdTemp = null;
            BodyDef bdTemp = new BodyDef();
            bdTemp.type = BodyDef.BodyType.StaticBody;
            if (mObj instanceof PolylineMapObject) {
                fdTemp = createPolylineFixture((PolylineMapObject) mObj);
            } else if (mObj instanceof RectangleMapObject) {
                fdTemp = createRectangleFixture((RectangleMapObject) mObj);
                bdTemp.position.set(getRectangleCoordinates((RectangleMapObject) mObj));
            } else if (mObj instanceof PolygonMapObject) {
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
        for (int i = 0; i < vecPoints.length; i++) {
            vecPoints[i] = new Vector2();
            vecPoints[i].x = fCoordinates[i * 2] / main.PIXELS_TO_METERS;
            vecPoints[i].y = fCoordinates[i * 2 + 1] / main.PIXELS_TO_METERS;
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
        psTemp.setAsBox(rectTemp.width / 2 / main.PIXELS_TO_METERS, rectTemp.height / 2 / main.PIXELS_TO_METERS);
        fdTemp.shape = psTemp;
        return fdTemp;

    }

    private FixtureDef createPolygonFixture(PolygonMapObject pmObj) {
        float[] fVerticies = pmObj.getPolygon().getTransformedVertices();
        Vector2[] vecPoints = new Vector2[fVerticies.length / 2];
        for (int i = 0; i < fVerticies.length / 2; i++) {
            vecPoints[i] = new Vector2();
            vecPoints[i].x = fVerticies[i * 2] / main.PIXELS_TO_METERS;
            vecPoints[i].y = fVerticies[i * 2 + 1] / main.PIXELS_TO_METERS;
        }
        PolygonShape psTemp = new PolygonShape();
        psTemp.set(vecPoints);
        FixtureDef fdTemp = new FixtureDef();
        fdTemp.shape = psTemp;
        return fdTemp;
    }

    private Vector2 getRectangleCoordinates(RectangleMapObject rmObj) {
        Rectangle rectTemp = rmObj.getRectangle();
        Vector2 vecLocation = new Vector2((rectTemp.getX() + rectTemp.width / 2) / main.PIXELS_TO_METERS, (rectTemp.getY() + rectTemp.height / 2) / main.PIXELS_TO_METERS);
        return vecLocation;
    }

}
