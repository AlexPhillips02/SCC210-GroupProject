package com.mygdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.Board.Board;

public class Creep extends Enemies
{
    public Creep(Board board, float x, float y, float movementSpeed)
    {
        super("core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f00.png", board, x, y, movementSpeed);
        createAnimations();
    }

    public void createAnimations()
    {
        //Walking downwards
        String[] downIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Front/Creep_F_f05.png"};

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 0; i < downIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(downIcons[i])));
        }

        //Number relates to speed of animation can be decreaed / sped up
        walkDown = new Animation<>(1/15f, frames);
        frames.clear();

        //Walking Upwards (Back of player)
        String[] upIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Back/Creep_B_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Back/Creep_B_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Back/Creep_B_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Back/Creep_B_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Back/Creep_B_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Back/Creep_B_f05.png",};

        for (int i = 0; i < upIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(upIcons[i])));
        }

        walkUp = new Animation<>(1/15f, frames);
        frames.clear();

        //Walking to the Right
        String[] rightIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f05.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Right/Creep_R_f06.png"};

        for (int i = 0; i < rightIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(rightIcons[i])));
        }

        walkRight = new Animation<>(1/15f, frames);
        frames.clear();

        //Walking to the left
        String[] leftIcons = {"core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f00.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f01.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f02.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f03.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f04.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f05.png",
        "core/assets/Bombing_Chap_Sprite_Set/Sprites/Creep/Left/Creep_L_f06.png"};

        for (int i = 0; i < leftIcons.length; i++) 
        {
            frames.add(new TextureRegion(new Texture(leftIcons[i])));
        }

        walkLeft = new Animation<>(1/15f, frames);
    }
}


