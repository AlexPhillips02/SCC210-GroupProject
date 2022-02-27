package com.mygdx.Sound.Sounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**@author victor, music class that creates an instance of sound. **/

public class Sounds {
    private Sound sound;

    public Sounds(String name){
        sound = (Sound) Gdx.audio.newSound(Gdx.files.internal(name));
    }

    public Sound getSound(){
        return sound;
    }

}
