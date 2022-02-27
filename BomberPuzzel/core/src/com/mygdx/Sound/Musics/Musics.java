package com.mygdx.Sound.Musics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * @author victor, music class that creates an instance of music. 
 */
public class Musics {
    private Music music;

    public Musics(String name){
        music = Gdx.audio.newMusic(Gdx.files.internal(name));
    }

    public Music getMusic(){
        return music;
    }

}