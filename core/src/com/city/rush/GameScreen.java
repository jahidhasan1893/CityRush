package com.city.rush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen implements Screen {
    SpriteBatch batch;
    OrthographicCamera camera;
    private TextureAtlas textureAtlas;
    private Animation animation;
    Texture bg1,bg2,bg3;
    Texture[] backgrounds;
    private float position_x=720;
    private float position_y=100;
    private float cur_pos=720;
    private float timePassed=0;

    public GameScreen(CityRush cityRush) {
        camera=new OrthographicCamera();
        camera.setToOrtho(false,1440,700);
        batch=new SpriteBatch();
        textureAtlas=new TextureAtlas(Gdx.files.internal("Images/runner.txt"));
        animation=new Animation(1/10f,textureAtlas.getRegions());
        bg1=new Texture("Images/bg1.png");
        bg2=new Texture("Images/bg2.png");
        bg3=new Texture("Images/bg3.png");
        backgrounds=new Texture[]{bg1,bg3,bg2};
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.52f,0.8f,0.92f,0);
        camera.position.set(cur_pos,350,0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        timePassed+=Gdx.graphics.getDeltaTime();
        for(int i=0;i<1000;i++)
        {
            batch.draw(backgrounds[i%3],i*1360,0,1440,700);
        }
        cur_pos=position_x+timePassed*350;
        //batch.draw((TextureRegion) animation.getKeyFrame(timePassed,true),cur_pos,position_y);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        bg1.dispose();
        bg2.dispose();
        bg3.dispose();
        textureAtlas.dispose();
    }
}
