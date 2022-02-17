package com.mygdx.Puzzles.ColourMatch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.Board.Board;


public class ColourTiles 
{

    protected int selectedColour;
    protected Texture defaultImage;
    protected float x;
    protected float y;
    protected Board board; 
    protected Rectangle collisionRectangle;
    public boolean active = true; 

    public static String[] colourType = {
        "/home/boatengv/h-drive/year2/SCC210/SCC210-GroupProject-main/BomberPuzzel/core/assets/purpleTile.jpg", //[0]
        "/home/boatengv/h-drive/year2/SCC210/SCC210-GroupProject-main/BomberPuzzel/core/assets/greenTile.png",  //[1]
        "/home/boatengv/h-drive/year2/SCC210/SCC210-GroupProject-main/BomberPuzzel/core/assets/orangeTile.png", //[2]
        "/home/boatengv/h-drive/year2/SCC210/SCC210-GroupProject-main/BomberPuzzel/core/assets/redTile.png",    //[3]
        "/home/boatengv/h-drive/year2/SCC210/SCC210-GroupProject-main/BomberPuzzel/core/assets/blueTile.png",   //[4]
        "/home/boatengv/h-drive/year2/SCC210/SCC210-GroupProject-main/BomberPuzzel/core/assets/yellowTile.jpg"  //[5]
    };

    public ColourTiles(int colNum, Board board, float x, float y){
        String imageURL = selectedColour(colNum);
        this.defaultImage = new Texture(imageURL);
        this.selectedColour = colNum;
        this.board = board;
        this.x = x;
        this.y = y;   

        collisionRectangle = new Rectangle(this.x, this.y, defaultImage.getWidth(), defaultImage.getHeight());    
    }

    private String selectedColour(int x) {
        return colourType[x];
    }

    public boolean getActive(){
        return this.active;
    }

    public int getselectedColourNumber(){
        return this.selectedColour;
    }

    public void colourTouched(){

    }


    public String getColour(int x){
        if(x == 0){
            return "purpleTile"; 
        }
        if(x == 1){
            return "greenTile";
        }
        if(x == 2){
            return "orangeTile";
        }
        if(x == 3){
            return "redTile";
        }
        if(x == 4){
            return "blueTile";
        }
        if(x == 5){
            return "yellowTile";
        }

        return "No colour";
    }

    public void Draw(SpriteBatch batch){
        batch.draw(defaultImage, x, y);
    }

    public Texture getImage(){
        return defaultImage;
    }

    public int getHeight(){
        return defaultImage.getHeight();
    }

    public int getWidth(){
        return defaultImage.getWidth();
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x)
    {
        this.x = x;
        collisionRectangle.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
        collisionRectangle.y = y;
    }

    public Rectangle getCollisionRectangle()
    {
        return collisionRectangle;
    }

    public void setActive(){
        this.active = false; 
    }
}
