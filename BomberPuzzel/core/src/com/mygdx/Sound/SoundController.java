package com.mygdx.Sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


/**
 * @author Alex Phillips, Victor
 * Plays the sound / music 
 **/
public class SoundController 
{
    /**
     * Plays music
     * @param m Music
     */
    public void playMusic(Music m)
    {
        m.play();
    }

    /**
     * Plays sounds
     * @param s Plays sounds
     */
    public void playMusic(Sound s)
    {
        s.play(1);
    }
}