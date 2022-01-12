package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public class BombIncrement extends Ability {
    public BombIncrement(Board board, float x, float y, Player player) {
        super("bomb_increased.png", board, x, y);
        this.player = player;
        player.setBombsMax(+1);
    }

}
