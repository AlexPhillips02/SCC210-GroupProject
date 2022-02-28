package com.mygdx.Sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


/**
 * @author Victor
 * Where sounds and music can be played
 **/
public class SoundController 
{
    //play the sound 
    public void playMusic(Music m)
    {
        m.play();
    }

    public void playMusic(Sound s)
    {
        s.play();
    }
}