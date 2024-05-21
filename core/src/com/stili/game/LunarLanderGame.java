package com.stili.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.stili.game.screens.GameScreen;

public class LunarLanderGame extends Game {
	private static LunarLanderGame instance;
	private int screenWidth, screenHeight;

    public LunarLanderGame() {
		instance = this;
	}
	
	@Override
	public void create () {
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();

        OrthographicCamera orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, screenWidth, screenHeight);
		orthographicCamera.position.set(400, 0, 0);

		setScreen(new GameScreen(orthographicCamera));

	}
}
