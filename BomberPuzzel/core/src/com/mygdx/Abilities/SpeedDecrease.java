package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov, Alex Phillips
 * A class for the ability which is randomly dropped for increasing bomb range.
 */
public class SpeedDecrease extends Ability{ //also deemed as SLOWNESS in the specification

    /**
     * Constructor for the abstract class of Abilities, all of them should be extended by this.
     * @param board    The game board
     * @param x        X coordinate of the entity
     * @param y        Y coordinate of the entity
     * @param player   the player which gets the ability.
     */
    public SpeedDecrease(Board board, float x, float y, Player player) {
        super("Abilties/Speed down-1.png (2).png", board, x, y, player, "Slowness", 5);
    }

    /**
     * Executed when the ability is activated
     */
    public void ActivateAbility()
    {
        float currentSpeed = entity.getMovementSpeed();
        entity.setMovementSpeed(currentSpeed - 50);
    }

    /**
     * Executed when the ability is deactivated
     */
    public void DeactivateAbility()
    {
        float currentSpeed = entity.getMovementSpeed();
        entity.setMovementSpeed(currentSpeed + 50);
    }
}

