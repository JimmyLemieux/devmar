package com.savingdata.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.TiledMapwithBox2d.game.RunGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = 3000;
                config.height = 800;
		new LwjglApplication(new RunGame(), config);
	}
}
