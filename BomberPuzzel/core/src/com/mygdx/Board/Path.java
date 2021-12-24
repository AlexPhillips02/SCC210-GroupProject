package com.mygdx.Board;

import com.badlogic.gdx.graphics.Texture;

public class Path extends Tile
{
    public Path()
    {
        image = new Texture("Path.jpg");
        isWalkable = true;
    }    
}