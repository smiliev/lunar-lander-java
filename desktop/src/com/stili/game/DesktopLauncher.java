package com.stili.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(800, 600);
		config.setTitle("LunarLanderJava");
		config.setBackBufferConfig(
				8, 8, 8, 8,    // Red, Green, Blue, Alpha bits
				16,            // Depth bits
				0,             // Stencil bits
				16              // Number of samples for multi-sampling (anti-aliasing)
		);

		new Lwjgl3Application(new LunarLanderGame(), config);

	}
}
