package com.city.rush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final CityRush gameScreen;
    SpriteBatch batch;
    OrthographicCamera camera;
    Texture bg1,bg2,bg3,bomb;
    Texture[] backgrounds;
    private TextureAtlas runAtlas,jumpAtlas,punchAtlas,jombieAtlas;
    private Animation runAnimation,jumpAnimation,punchAnimation,jombieAnimation;
    private FreeTypeFontGenerator freeTypeFontGenerator,generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter,parameter;
    private BitmapFont font,score,note;
    private float position_x=720;
    private float position_y=0;
    private float cur_pos=720;
    private float jombie_pos;
    private float bomb_pos;
    private float timePassed=0;
    private float jump_timer=0.9f;
    private boolean jump_chk=false;
    private int point=0;
    private int x=1;
    private String str;

    public GameScreen(CityRush cityRush) {
        this.gameScreen=cityRush;
        camera=new OrthographicCamera();
        camera.setToOrtho(false,1440,700);
        runAtlas=new TextureAtlas(Gdx.files.internal("Animation1/run.txt"));
        runAnimation=new Animation(1/15f,runAtlas.getRegions());
        jumpAtlas=new TextureAtlas(Gdx.files.internal("Animation2/jump.txt"));
        jumpAnimation=new Animation(1/15f,jumpAtlas.getRegions());
        punchAtlas=new TextureAtlas(Gdx.files.internal("Animation3/punch.txt"));
        punchAnimation=new Animation(1/15f,punchAtlas.getRegions());
        jombieAtlas=new TextureAtlas(Gdx.files.internal("Animation4/jombie.txt"));
        jombieAnimation=new Animation(1/10f,jombieAtlas.getRegions());
        font=new BitmapFont();
        score=new BitmapFont();
        note=new BitmapFont();
        freeTypeFontGenerator=new FreeTypeFontGenerator(Gdx.files.internal("Fonts/HighlandGothic.ttf"));
        freeTypeFontParameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.color= Color.BLACK;
        freeTypeFontParameter.size=30;
        font=freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        score=freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        generator=new FreeTypeFontGenerator(Gdx.files.internal("Fonts/TYPEWR.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color=Color.WHITE;
        parameter.size=30;
        note=generator.generateFont(parameter);
        batch=new SpriteBatch();
        bg1=new Texture("Images/bg1.png");
        bg2=new Texture("Images/bg2.png");
        bg3=new Texture("Images/bg3.png");
        bomb=new Texture(Gdx.files.internal("Images/bomb.png"));
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
        font.draw(batch,"Score : ",cur_pos+430,650);
        str=Integer.toString(point);
        score.draw(batch,str,cur_pos+550,650);
        //System.out.println(timePassed);
        if(timePassed>3*x && timePassed<3*x+0.1)
        {
            bomb_pos=cur_pos+50;
            x++;
        }
        if(timePassed>5*x && timePassed<5*x+0.1)
        {
            jombie_pos=cur_pos+450;
            x++;
        }
        jombie_pos--;
        batch.draw((TextureRegion) jombieAnimation.getKeyFrame(timePassed,true),jombie_pos,position_y);
        batch.draw(bomb,bomb_pos,position_y+85,60,30);
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            point+=1;
            cur_pos=position_x+timePassed*350;
            batch.draw((TextureRegion) jumpAnimation.getKeyFrame(timePassed,true),cur_pos-550,position_y);
            jumpAnimation.setFrameDuration(0.1f);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.B))
        {
            jombie_pos++;
            batch.draw((TextureRegion) punchAnimation.getKeyFrame(timePassed,true),cur_pos-550,position_y);
        }
        else
        {
            point+=1;
            cur_pos=position_x+timePassed*350;
            batch.draw((TextureRegion) runAnimation.getKeyFrame(timePassed,true),cur_pos-550,position_y);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            //dispose();
            gameScreen.setScreen(new MainMenuScreen(gameScreen));
            //dispose();
        }
        if(timePassed<3.0f)
        {
            note.draw(batch,"*press esc to return Home Menu",cur_pos-270,70);
        }
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
        bg1.dispose();
        bg2.dispose();
        bg3.dispose();
        batch.dispose();
        runAtlas.dispose();
        jumpAtlas.dispose();
        punchAtlas.dispose();
        freeTypeFontGenerator.dispose();
        font.dispose();
        bomb.dispose();
    }
}
