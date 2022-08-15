package com.city.rush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jogamp.opengl.util.texture.Texture;

public class ControlScreen implements Screen {
    private CityRush control;
    SpriteBatch batch;


    public ControlScreen(CityRush mainMenu) {
        this.control=mainMenu;
        batch=new SpriteBatch();


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

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

    }
}
