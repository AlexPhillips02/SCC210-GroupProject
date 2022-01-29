package com.mygdx.Abilities;

import com.mygdx.Board.Board;
import com.mygdx.Player.Player;

/**
 * @author Alex Chalakov, Alex Phillips
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
        super("core/assets/Bombing_Chap_Sprite_Set/Sprites/Powerups/BombPowerup.png", board, x, y, player);
    }

    public void ActivateAbility()
    {
        int currentMaxBombs = player.getBombsMax();
        player.setBombsMax(currentMaxBombs + 1);
    }

    public void DeactivateAbility()
    {
        int currentMaxBombs = player.getBombsMax();
        player.setBombsMax(currentMaxBombs - 1);
    }
}
