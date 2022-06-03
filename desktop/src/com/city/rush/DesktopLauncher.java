package com.city.rush;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.city.rush.CityRush;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("City Rush");
		config.setWindowedMode(1440,780);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new CityRush(), config);
	}
}
