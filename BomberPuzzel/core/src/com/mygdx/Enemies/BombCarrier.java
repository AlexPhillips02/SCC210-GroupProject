package com.mygdx.Enemies;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public class BombCarrier extends PlayerTrackingEnemy
{
    public BombCarrier(Board board, float x, float y, float movementSpeed, Player player)
    {
        super("core/assets/BombExplosionThings/pixil-frame-0.png", board, x, y, movementSpeed, player, 10);
    }
}
