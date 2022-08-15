package com.city.rush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ControlScreen implements Screen {
    private CityRush control;
    SpriteBatch batch;
    Texture texture;
    private float timePassed=0;
    BitmapFont font,font2;
    FreeTypeFontGenerator generator,generator2;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter,parameter2;


    public ControlScreen(CityRush mainMenu) {
        this.control=mainMenu;
        batch=new SpriteBatch();
        texture =new Texture(Gdx.files.internal("Images/highScore.png"));
        generator=new FreeTypeFontGenerator(Gdx.files.internal("Fonts/TYPEWR.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=30;
        parameter.color= Color.BLACK;
        font=generator.generateFont(parameter);
        generator2=new FreeTypeFontGenerator(Gdx.files.internal("Fonts/TYPEWR.ttf"));
        parameter2=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size=30;
        parameter2.color=Color.WHITE;
        font2=generator2.generateFont(parameter2);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        batch.begin();
        timePassed+=Gdx.graphics.getDeltaTime();
        batch.draw(texture,-30, 30, 1700, 750);
        font2.draw(batch,"# Press A to Jump",560,370);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            control.setScreen(new MainMenuScreen(control));
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
        generator.dispose();
        batch.dispose();
        texture.dispose();
        font.dispose();
        font2.dispose();
    }
}
