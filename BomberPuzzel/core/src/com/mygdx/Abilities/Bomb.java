package com.mygdx.Abilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;


public class Bomb extends Ability{

    protected int time;
    protected int bombHeight;
    protected int bombWidth;

    private Animation<TextureRegion> bombExplosion;

    public enum State {
        IDLE,
        READY,
        EXPLODED
    }

    protected State state;

    public Bomb(Board board, float x, float y, int time) 
    {
        super("bomb_1.png", board, x, y);
        this.time = time;
        state = State.READY;

        bombController(this);
        createAnimations();
    }

    protected Bomb bomb;

    public void bombController (Bomb bomb)
    {
        this.bomb = bomb;
        bombHeight = bomb.getHeight();
        bombWidth = bomb.bombWidth;
    }


    public void createAnimations()
    {
        Array<TextureRegion> frames = new Array<>();

        String[] explosionBomb = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomb/Bomb_f01.png",
                "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomb/Bomb_f02.png",
                "core/assets/Bombing_Chap_Sprite_Set/Sprites/Bomb/Bomb_f03.png"};

        for (int i = 0; i< explosionBomb.length; i++)
        {
            frames.add(new TextureRegion(new Texture((explosionBomb[i]))));
        }

        bombExplosion = new Animation<>(1/15f, frames);
        frames.clear();
    }
}
