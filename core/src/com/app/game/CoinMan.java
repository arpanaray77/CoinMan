package com.app.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Random;

public class CoinMan extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture[] man;
	int manState=0;
	int pause=0;
	float gravity=0.5f;
	float velocity=0;
	int manY=0;
	ArrayList<Integer> coinXs=new ArrayList<>();
    ArrayList<Integer> coinYs=new ArrayList<>();
    Texture coin;
    int coinCount;
	ArrayList<Integer> bombXs=new ArrayList<>();
	ArrayList<Integer> bombYs=new ArrayList<>();
	Texture bomb;
	int bombCount;
    Random random;


	@Override
	public void create () {

	    batch = new SpriteBatch();
	    background=new Texture("background.png");

	    man=new Texture[4];
	    man[0]=new Texture("frame1.png");
        man[1]=new Texture("frame2.png");
        man[2]=new Texture("frame3.png");
        man[3]=new Texture("frame4.png");
        manY=Gdx.graphics.getHeight()/2;
        coin=new Texture("coin128.png");
        bomb=new Texture("bomb128.png");
        random=new Random();
	}

	public void makeCoin() {
		float height = 350 + random.nextFloat() * Gdx.graphics.getHeight();
		if (height < (Gdx.graphics.getHeight()-128)) {
			coinYs.add((int) height);
			coinXs.add(Gdx.graphics.getWidth());
		}
	}

	public void makeBomb() {
		float height = 350 + random.nextFloat() * Gdx.graphics.getHeight();
		if (height < (Gdx.graphics.getHeight() - 128)) {
			bombYs.add((int) height);
			bombXs.add(Gdx.graphics.getWidth());
		}
	}

	@Override
	public void render () {
     batch.begin();
     batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

     if(bombCount<500)
		{
			bombCount++;
		}
		else
		{
			bombCount=0;
			makeBomb();
		}
     for (int i=0;i<bombXs.size();i++)
		{
			batch.draw(bomb,bombXs.get(i),bombYs.get(i));
			bombXs.set(i,bombXs.get(i)-4);
		}

	if(coinCount<100)
	 {
	 	coinCount++;
	 }
     else
	 {
	 	coinCount=0;
	 	makeCoin();
	 }
     for (int i=0;i<coinXs.size();i++)
	 {
	 	batch.draw(coin,coinXs.get(i),coinYs.get(i));
	 	coinXs.set(i,coinXs.get(i)-4);
	 }

     if(Gdx.input.justTouched()) {
     	velocity-=10;
	 }
     if(pause<8)
	  {
         pause++;
     }
     else
	 {
	 	pause=0;
		 if(manState<3)
			 manState++;
		 else
			 manState=0;
	 }

     velocity+=gravity;
     manY-=velocity;

     if(manY<=320)
	 {
	 	manY=320;
	 }
     batch.draw(man[manState],Gdx.graphics.getWidth()/2-man[manState].getWidth()/2,manY);
     batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
