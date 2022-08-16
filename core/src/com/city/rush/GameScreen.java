package com.city.rush;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

public class GameScreen implements Screen {
    final CityRush gameScreen;
    SpriteBatch batch;
    OrthographicCamera camera;
    Texture bg1, bg2, bg3, bomb, game_over,drone;
    Texture[] backgrounds;
    private TextureAtlas runAtlas, jumpAtlas, punchAtlas, jombieAtlas;
    private Animation runAnimation, jumpAnimation, punchAnimation, jombieAnimation;
    private FreeTypeFontGenerator freeTypeFontGenerator, generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter freeTypeFontParameter, parameter;
    private BitmapFont font, score, note;
    private float position_x = 720;
    private float position_y = 0;
    private float cur_pos = 720;
    private float jombie_pos;
    private float bomb_pos;
    private float drone_pos;
    private float timePassed = 0;
    private float jump_timer = 0.9f;
    private boolean jump_chk = false;
    private int point = 0;
    private int x = 1,y=1;
    private String str;
    private Rectangle bomb_i;
    private Rectangle player_i;
    private Rectangle drone_i;
    boolean is_game_over = false;


    public GameScreen(CityRush cityRush) {
        this.gameScreen = cityRush;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 700);
        bomb_i = new Rectangle();
        player_i = new Rectangle();
        drone_i=new Rectangle();
        bomb_i.height = 11;
        bomb_i.width = 1;
        player_i.width = 150;
        player_i.height = 120;
        player_i.y=100;
        bomb_i.y=0;
        drone_i.height=100;
        drone_i.width=10;

        runAtlas = new TextureAtlas(Gdx.files.internal("Animation1/run.txt"));
        runAnimation = new Animation(1 / 15f, runAtlas.getRegions());
        jumpAtlas = new TextureAtlas(Gdx.files.internal("Animation2/jump.txt"));
        jumpAnimation = new Animation(1 / 15f, jumpAtlas.getRegions());
        punchAtlas = new TextureAtlas(Gdx.files.internal("Animation3/punch.txt"));
        punchAnimation = new Animation(1 / 15f, punchAtlas.getRegions());
        jombieAtlas = new TextureAtlas(Gdx.files.internal("Animation4/jombie.txt"));
        jombieAnimation = new Animation(1 / 10f, jombieAtlas.getRegions());
        font = new BitmapFont();
        score = new BitmapFont();
        note = new BitmapFont();
        freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/HighlandGothic.ttf"));
        freeTypeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        freeTypeFontParameter.color = Color.BLACK;
        freeTypeFontParameter.size = 30;
        font = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        score = freeTypeFontGenerator.generateFont(freeTypeFontParameter);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/TYPEWR.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 30;
        note = generator.generateFont(parameter);
        batch = new SpriteBatch();
        bg1 = new Texture("Images/bg1.png");
        bg2 = new Texture("Images/bg2.png");
        bg3 = new Texture("Images/bg3.png");
        bomb = new Texture(Gdx.files.internal("Images/bomb.png"));
        drone=new Texture(Gdx.files.internal("Images/drone.png"));
        game_over = new Texture(Gdx.files.internal("Images/game_over.png"));
        backgrounds = new Texture[]{bg1, bg3, bg2};
        gameScreen.pref=Gdx.app.getPreferences("game preferences");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.52f, 0.8f, 0.92f, 0);
        camera.position.set(cur_pos, 350, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        for (int i = 0; i < 1000; i++) {
            batch.draw(backgrounds[i % 3], i * 1360, 0, 1440, 700);
        }
        if (is_game_over == false) {
            font.draw(batch, "Score : ", cur_pos + 430, 650);
            str = Integer.toString(point);
            score.draw(batch, str, cur_pos + 550, 650);
            //System.out.println(timePassed);
            if (timePassed > 3 * x && timePassed < 3 * x + 0.1) {
                bomb_pos = cur_pos + 450;
                x++;
            }
            /*if (timePassed > 5 * x && timePassed < 5 * x + 0.1) {
                drone_pos = cur_pos + 500;
                x++;
            }*/
            if (timePassed > 5 * y && timePassed < 5 * y + 0.1) {
                drone_pos = cur_pos + 400;
                y++;
            }

            //jombie_pos--;
            // batch.draw((TextureRegion) jombieAnimation.getKeyFrame(timePassed,true),jombi
            // e_pos,position_y);
            batch.draw(bomb, bomb_pos, position_y + 85, 60, 30);
            batch.draw(drone,drone_pos,position_y+85+170,120,50);
            //System.out.println(drone_i.y-player_i.y);
            if (player_i.intersects(bomb_i)) {
            /*System.out.println(player_i.x-bomb_i.x);
            System.out.println("Finished!!");*/
                is_game_over = true;
                //System.out.println("Finished!!!");
                //System.out.println(player_i.y - bomb_i.y);
            }
            if (player_i.intersects(drone_i)) {
            /*System.out.println(player_i.x-bomb_i.x);
            System.out.println("Finished!!");*/
                is_game_over = true;
                //System.out.println("Finished!!!");
                //System.out.println(player_i.y - bomb_i.y);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                point += 1;
                cur_pos = position_x + timePassed * 350;
                batch.draw((TextureRegion) jumpAnimation.getKeyFrame(timePassed, true), cur_pos - 550, position_y+50);
                bomb_i.x = (int) bomb_pos;
                bomb_i.y = (int) position_y + 85;
                player_i.x = (int) cur_pos - 550;
                player_i.y = (int) position_y + 95+50;
                drone_i.x=(int)drone_pos;
                drone_i.y=(int)position_y+85+170;
                jumpAnimation.setFrameDuration(0.1f);
            } else if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                jombie_pos++;
                batch.draw((TextureRegion) punchAnimation.getKeyFrame(timePassed, true), cur_pos - 550, position_y);
            } else {
                point += 1;
                cur_pos = position_x + timePassed * 350;
                batch.draw((TextureRegion) runAnimation.getKeyFrame(timePassed, true), cur_pos - 550, position_y);
                bomb_i.x = (int) bomb_pos;
                bomb_i.y = (int) position_y + 85;
                player_i.x = (int) cur_pos - 550;
                player_i.y = (int) position_y + 95;
                drone_i.x=(int)drone_pos;
                drone_i.y=(int)position_y+85+170;
            }
        }
        else
        {
            if(point>gameScreen.high_score)
            {
                gameScreen.pref.putInteger("High score",point-1);
                gameScreen.pref.flush();
            }
            gameScreen.high_score=gameScreen.pref.getInteger("High score");
            //System.out.println(gameScreen.high_score);
            batch.draw(game_over,cur_pos-280,300,600,400);
            score.draw(batch,"Score: "+str,cur_pos-80,330);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            //dispose();
            gameScreen.setScreen(new MainMenuScreen(gameScreen));
            //dispose();
        }
        if (timePassed < 3.0f) {
            note.draw(batch, "*press esc to return Home Menu", cur_pos - 270, 70);
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
        drone.dispose();
    }
}
