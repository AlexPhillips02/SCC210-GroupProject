package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public class SpeedIncrease extends Ability{
    public SpeedIncrease(Board board, float x, float y, Player player) {
        super("speed_increased.png", board, x, y);
        this.player = player;
        player.setMovementSpeed(-0.25F);
    }
}
