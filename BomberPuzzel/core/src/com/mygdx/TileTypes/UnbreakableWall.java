package com.mygdx.TileTypes;

import com.mygdx.Board.Tile;

/**
 * @author Alex Phillips
 * Unbreakable wall, cannot be broken by a bomb (Used for outer walls)
 */
public class UnbreakableWall extends Tile
{
    public UnbreakableWall()
    {
        super("Wall/HardBlockTest.png");
    }
}