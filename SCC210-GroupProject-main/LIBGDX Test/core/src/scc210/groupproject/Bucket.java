package scc210.groupproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class Bucket extends ApplicationAdapter
{
    private Texture bucketImage;
    private Rectangle bucket;
    private float x;
    private float y;

    public Bucket(Texture bucketImage, Rectangle bucket)
    {
        this.bucket = bucket;
        this.bucketImage = bucketImage;
        this.x = 0;
        this.y = 0;
        setDefaults();
    }

    public void setDefaults()
    {
        bucket.x = 800 / 2 - 64 / 2;
        bucket.y = 20;
        x = bucket.x;
        y = bucket.y;
        bucket.width = 64;
        bucket.height = 64;
    }

    public Texture getBucketImage() {
        return bucketImage;
    }

    public Rectangle getBucket() {
        return bucket;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        bucket.x = x;
    }

    public void setY(float y) {
        this.y = y;
        bucket.y = y;
    }
}
