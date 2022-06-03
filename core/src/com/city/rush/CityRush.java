package com.city.rush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.utils.ScreenUtils;

public class CityRush extends Game  {
	SpriteBatch batch;
	Texture mainMenuBg;
	BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenuBg = new Texture("mainMenu.png");
		font=new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		mainMenuBg.dispose();
		font.dispose();
	}
}
