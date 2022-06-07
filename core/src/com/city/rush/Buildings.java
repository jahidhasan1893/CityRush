package com.city.rush;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class Buildings {
    int pos_y;
    int pos_x;
    Texture texture;
    Buildings(int x,int y,Texture tx)
    {
        texture=tx;
        pos_x= x;
        pos_y=y;
    }
}
