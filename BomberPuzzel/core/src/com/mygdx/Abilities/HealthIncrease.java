package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov
 * A class for the ability which is randomly dropped for increasing bomb capacity.
 */
public class HealthIncrease extends Ability { //also deemed as SHIELD in the specification

    /**
     * Constructor for the abstract class of Abilities, all of them should be extended by this.
     * @param board    The game board
     * @param x        X coordinate of the entity
     * @param y        Y coordinate of the entity
     * @param player   the player which gets the ability.
     */
    public HealthIncrease(Board board, float x, float y, Player player) {
        super("Abilties/Health.png", board, x, y, player, "Extra Heart", 0);
    }

    /**
     * Executed when the ability is activated
     */
    public void ActivateAbility()
    {
        int currentHealth = ((Player) entity).getHealth();
        entity.setHealth(currentHealth + 1);
    }

    /**
     * Executed when the ability is deactivated
     */
    public void DeactivateAbility()
    {
        //health stays the same after ability period expires
    }
}
