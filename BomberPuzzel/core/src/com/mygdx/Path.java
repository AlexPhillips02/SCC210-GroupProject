package com.mygdx;

import com.badlogic.gdx.graphics.Texture;

public class Path extends Tile
{
    public Path()
    {
        image = new Texture("Path.jpg");
        isWalkable = true;
    }    
}