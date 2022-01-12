package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

public class BombRange extends Ability {
    public BombRange(Board board, float x, float y, Player player) {
        super("bomb_ranged.png", board, x, y);
        this.player = player;
        player.setBombsRange(+1);
    }
}
