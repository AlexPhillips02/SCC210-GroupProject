package com.mygdx.Enemies;

import com.mygdx.Abilities.Bomb;
import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public class BombCarrier extends PlayerTrackingEnemy
{
    private int explosionRange = 2;
    private int bombsMax = 1;

    public BombCarrier(Board board, float x, float y, float movementSpeed, Player player)
    {
        super("core/assets/BombExplosionThings/pixil-frame-0.png", board, x, y, movementSpeed, player, 10);
        this.health = 1;
    }

    public void Attack(Player player)
    {
        System.out.println("Bomb carrier hit player and placed bomb");
        if (bombsMax > 0) 
        {
            new Bomb(board, x, y, this, explosionRange);
            bombsMax--;   
        }

        this.reduceHealth();
    }
}
