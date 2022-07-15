package com.city.rush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class AboutUsScreen implements Screen {
    final CityRush aboutUs;
    SpriteBatch batch;
    Texture aboutUsBg;
    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    BitmapFont font;
    private float timePassed=0;
    public AboutUsScreen(CityRush cityRush) {
        this.aboutUs=cityRush;
        batch=new SpriteBatch();
        aboutUsBg=new Texture(Gdx.files.internal("Images/aboutUs.png"));
        generator=new FreeTypeFontGenerator(Gdx.files.internal("Fonts/TYPEWR.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=30;
        parameter.color= Color.BLACK;
        font=generator.generateFont(parameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        batch.begin();
        timePassed+=Gdx.graphics.getDeltaTime();
        batch.draw(aboutUsBg, 30, 30, 1540, 750);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            aboutUs.setScreen(new MainMenuScreen(aboutUs));
        }
        if(timePassed>0.5)
        {
            font.draw(batch,"*press esc to return Home Menu",450,60);
        }
        if(timePassed>1) timePassed=0;
        batch.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        aboutUsBg.dispose();
        generator.dispose();
        font.dispose();
    }
}
