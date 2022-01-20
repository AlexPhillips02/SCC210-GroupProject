package com.mygdx.Abilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public abstract class Ability {
    protected Texture defaultImage;
    protected Board board;
    protected float x;
    protected float y;
    protected Player player;
    protected Rectangle collisionRectangle; //will see if its needed

    protected Animation<TextureRegion> currentAnimation;
    private float elapsedTime = 0f;

    public Ability(String imageURL, Board board, float x, float y)
    {
        defaultImage = new Texture(imageURL);
        this.board = board;
        this.x = x;
        this.y = y;

        collisionRectangle = new Rectangle(x, y, defaultImage.getWidth(), defaultImage.getHeight());
    }

    public void setAnimation(Animation<TextureRegion> animation)
    {
        currentAnimation = animation;
    }

    public void Draw(SpriteBatch batch)
    {
        if (currentAnimation == null){
            batch.draw(defaultImage , x, y);
        } else {
            batch.draw(currentAnimation.getKeyFrame(elapsedTime, true), x, y);
            elapsedTime += Gdx.graphics.getDeltaTime();
        }
    }

    public Texture getImage() {
        return defaultImage;
    }

    public int getHeight()
    {
        return defaultImage.getHeight();
    }

    public int getWidth()
    {
        return defaultImage.getWidth();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        collisionRectangle.x = x;
    }

    public void setY(float y) {
        this.y = y;
        collisionRectangle.y = y;
    }
}
