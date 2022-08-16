package com.city.rush;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;


public class MainMenuScreen implements Screen{
    final CityRush mainMenu;
    private int mainMenuSelected = 0;
    private final int maxMainMenu=4;


    public MainMenuScreen(final CityRush cityRush) {
        this.mainMenu = cityRush;
        mainMenu.generator = new FreeTypeFontGenerator(Gdx.files.internal("Fonts/Peinture Fraiche.ttf"));
        mainMenu.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        mainMenu.parameter.color = Color.WHITE;
        mainMenu.parameter.size = 45;
        mainMenu.fontArray[1] = mainMenu.generator.generateFont(mainMenu.parameter);
        mainMenu.fontArray[2] = mainMenu.generator.generateFont(mainMenu.parameter);
        mainMenu.fontArray[3] = mainMenu.generator.generateFont(mainMenu.parameter);
        mainMenu.fontArray[4] = mainMenu.generator.generateFont(mainMenu.parameter);
        mainMenu.parameter.color = Color.FIREBRICK;
        mainMenu.parameter.size = 45;
        mainMenu.fontArray[0] = mainMenu.generator.generateFont(mainMenu.parameter);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,0);

        /*id=music.play(0.5f);
        music.setLooping(id,true);*/
        mainMenu.batch.begin();
        mainMenu.batch.draw(mainMenu.mainMenuBg, 0, -50, 1440, 750);
        mainMenu.fontArray[0].draw(mainMenu.batch, "New Game", 960, 550);
        mainMenu.fontArray[1].draw(mainMenu.batch, "High Score", 960, 470);
        mainMenu.fontArray[2].draw(mainMenu.batch, "Controls", 960, 390);
        mainMenu.fontArray[3].draw(mainMenu.batch, "About Us", 960, 310);
        mainMenu.fontArray[4].draw(mainMenu.batch, "Exit", 960, 230);
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            moveUp();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            moveDown();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
        {
            if(mainMenuSelected==0)
            {
                //dispose();
                mainMenu.setScreen(new GameScreen(mainMenu));
            }
            if(mainMenuSelected==1)
            {
                mainMenu.setScreen(new HighScoreScreen(mainMenu));
            }
            if(mainMenuSelected==2)
            {
                mainMenu.setScreen(new ControlScreen(mainMenu));
            }
            if(mainMenuSelected==3)
            {
                mainMenu.setScreen(new AboutUsScreen(mainMenu));
            }
            if(mainMenuSelected==4)
            {
                System.exit(0);
            }
        }
        mainMenu.batch.end();
    }

    private void moveUp() {
        if (mainMenuSelected - 1 >= 0) {
            mainMenu.parameter.color=Color.WHITE;
            mainMenu.fontArray[mainMenuSelected]=mainMenu.generator.generateFont(mainMenu.parameter);
            mainMenuSelected--;
            mainMenu.parameter.color=Color.FIREBRICK;
            mainMenu.fontArray[mainMenuSelected]=mainMenu.generator.generateFont(mainMenu.parameter);
        }
    }

    private void moveDown() {
        if(mainMenuSelected+1<=maxMainMenu)
        {
            mainMenu.parameter.color=Color.WHITE;
            mainMenu.fontArray[mainMenuSelected]=mainMenu.generator.generateFont(mainMenu.parameter);
            mainMenuSelected++;
            mainMenu.parameter.color=Color.FIREBRICK;
            mainMenu.fontArray[mainMenuSelected]=mainMenu.generator.generateFont(mainMenu.parameter);
        }
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

    }

}
