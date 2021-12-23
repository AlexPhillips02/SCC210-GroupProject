package com.mygdx;

import com.badlogic.gdx.graphics.Texture;

public class Wall extends Tile
{
    public Wall()
    {
        image = new Texture("Wall.jpg");
        isWalkable = false;
    }
}