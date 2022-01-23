package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov
 * A class for the ability which is randomly dropped for increasing bomb capacity.
 */
public class BombIncrement extends Ability {

    /**
     * A constructor for the ability.
     * @param board The game board
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param player the player which gets the ability.
     */
    public BombIncrement(Board board, float x, float y, Player player) {
        super("bomb_increased.png", board, x, y, player);
        player.setBombsMax(+1);
    }
}
