package com.city.rush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class CityRush extends Game  {
	SpriteBatch batch;
	Texture mainMenuBg,button;
	BitmapFont menu1,menu2,menu3,menu4,menu5;
	BitmapFont[] fontArray;
	FreeTypeFontGenerator generator;
	FreeTypeFontGenerator.FreeTypeFontParameter parameter;

	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenuBg = new Texture("Images/mainMenu.png");
        button=new Texture("Images/button.png");
		menu1=new BitmapFont();
		menu2=new BitmapFont();
		menu3=new BitmapFont();
		menu4=new BitmapFont();
		menu5=new BitmapFont();
		fontArray=new BitmapFont[]{menu1,menu2,menu3,menu4,menu5};
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
		menu1.dispose();
		menu2.dispose();
		menu3.dispose();
		menu4.dispose();
		menu5.dispose();
		generator.dispose();
	}
}
