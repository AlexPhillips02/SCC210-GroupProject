package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov, Alex Phillips
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
        super("Speed-1.png.png", board, x, y, player, "Fast Feet", 1);
    }

    public void ActivateAbility() 
    {
        float currentSpeed = entity.getMovementSpeed();
        entity.setMovementSpeed(currentSpeed + 50);
    }

    public void DeactivateAbility()
    {
        float currentSpeed = entity.getMovementSpeed();
        entity.setMovementSpeed(currentSpeed - 50);
    }
}
