package com.stili.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.stili.game.screens.GameScreen;

public class LunarLanderGame extends Game {
	private static LunarLanderGame instance;
	private int screenWidth, screenHeight;
	private OrthographicCamera orthographicCamera;

	public LunarLanderGame() {
		instance = this;
	}
	
	@Override
	public void create () {
		this.screenWidth = Gdx.graphics.getWidth();
		this.screenHeight = Gdx.graphics.getHeight();

		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false, screenWidth, screenHeight);

		setScreen(new GameScreen(orthographicCamera));

	}
}
