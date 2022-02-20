package com.mygdx.TileTypes;

/**
 * @author Alex Phillips
 */
public class SoftWall extends BreakableWall
{
    /**
     * Type of breakable wall (only takes 1 hit with bomb)
     */
    public SoftWall()
    {
        super("Bombing_Chap_Sprite_Set/Sprites/Blocks/ExplodableBlock.png", 1);
    }
}
