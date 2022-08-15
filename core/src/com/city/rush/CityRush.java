package com.city.rush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
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
    Sound music;
	long id;
	Preferences pref;
	public int high_score=0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenuBg = new Texture("Images/mainMenu.png");
		menu1=new BitmapFont();
		menu2=new BitmapFont();
		menu3=new BitmapFont();
		menu4=new BitmapFont();
		menu5=new BitmapFont();
        music= Gdx.audio.newSound(Gdx.files.internal("music/peritune-spook4.ogg"));
		fontArray=new BitmapFont[]{menu1,menu2,menu3,menu4,menu5};
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {

		super.render();
		id=music.play(0.5f);
		music.setLooping(id,true);
		//music.setVolume(id,0.5f);
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
		music.dispose();
	}
}
