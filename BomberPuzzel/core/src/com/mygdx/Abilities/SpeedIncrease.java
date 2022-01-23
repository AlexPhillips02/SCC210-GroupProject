package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov
 * A class for the ability which is randomly dropped for increasing bomb range.
 */
public class SpeedIncrease extends Ability{

    /**
     * A constructor for the ability.
     * @param board The game board
     * @param x X coordinate of the entity
     * @param y Y coordinate of the entity
     * @param player the player which gets the ability.
     */
    public SpeedIncrease(Board board, float x, float y, Player player) {
        super("speed_increased.png", board, x, y, player);
        player.setMovementSpeed(-0.25F);
    }
}
