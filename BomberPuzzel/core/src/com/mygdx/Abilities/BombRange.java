package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov
 * A class for the ability which is randomly dropped for increasing bomb range.
 */
public class BombRange extends Ability {

    /**
     * A constructor for the ability.
     * @param board The game board
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param player the player which gets the ability.
     */
    public BombRange(Board board, float x, float y, Player player) {
        super("bomb_ranged.png", board, x, y);
        this.player = player;
        player.setBombsRange(+2);
    }
}
