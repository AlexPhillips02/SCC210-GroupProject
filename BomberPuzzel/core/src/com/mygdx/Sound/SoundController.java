package com.mygdx.Sound;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.mygdx.Sound.Sounds.*;
import com.mygdx.Sound.Musics.Musics;


/**@author victor, where sounds and music can be played*/

public class SoundController {
    private Sound sound;
    public Music music; 

    //play the sound 
    public void playSound(Sounds s){
        s.getSound().play(1);
    }

    public void playMusic(Musics m){
        music = m.getMusic();
        m.getMusic().play();
    }

    
}