package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.Driver;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Puzzle Bomber");
		config.setWindowedMode(Driver.WIDTH, Driver.HEIGHT);
		config.setResizable(false);	

		new Lwjgl3Application(new Driver(), config);
  }
}
