package scc210.groupproject;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Driver extends ApplicationAdapter 
{
	private SpriteBatch batch;
	private Texture img;
	private Texture bucketImage;
	private Texture dropImage;
	private OrthographicCamera camera;
	private Rectangle bucket;
	private Array<Rectangle> raindrops;
	private long lastDropTime;
	private Bucket bucketClass;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		bucketImage = new Texture("bucket.png");
		dropImage = new Texture("drop.png");

		bucket = new Rectangle();
		bucketClass = new Bucket(bucketImage, bucket);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		raindrops = new Array<Rectangle>();
		spawnRaindrop();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		camera.update();

		Texture temp = bucketClass.getBucketImage();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(bucketClass.getBucketImage(), bucketClass.getX(), bucketClass.getY());
		batch.end();

		//Click and drag bucket
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			bucketClass.setX((touchPos.x - 64 / 2));
		}

		//Use arrow keys for bucket
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			bucketClass.setX(bucketClass.getX() - (200 * Gdx.graphics.getDeltaTime()));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			bucketClass.setX(bucketClass.getX() + (200 * Gdx.graphics.getDeltaTime()));
		}

		//Ensure bucket isnt over edge of screen
		if(bucketClass.getX() < 0)
		{
			bucketClass.setX(0);
		}
		if(bucketClass.getX() > 800 - 64)
		{
			bucketClass.setX(800 - 64);
		}

		//Raindrops
		if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

		for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
			Rectangle raindrop = iter.next();
			raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if(raindrop.y + 64 < 0) iter.remove();

			if(raindrop.overlaps(bucketClass.getBucket())) {
				iter.remove();
			}
		}

		batch.begin();
		//batch.draw(bucketClass.getImage(), bucket.x, bucket.y);

		for(Rectangle raindrop: raindrops) {
			batch.draw(dropImage, raindrop.x, raindrop.y);
		}
		batch.end();
	}

	private void spawnRaindrop() {
		Rectangle raindrop = new Rectangle();
		raindrop.x = MathUtils.random(0, 800-64);
		raindrop.y = 480;
		raindrop.width = 64;
		raindrop.height = 64;
		raindrops.add(raindrop);
		lastDropTime = TimeUtils.nanoTime();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

		dropImage.dispose();
		bucketImage.dispose();
	}
}
