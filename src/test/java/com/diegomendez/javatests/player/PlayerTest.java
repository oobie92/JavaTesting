package com.diegomendez.javatests.player;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PlayerTest {

    @Mock
    Dice dice;

    @InjectMocks
    Player player = new Player(dice, 3);

    @Test
    public void loseWhenDiceNumberIsTooLow() {
        when(dice.roll()).thenReturn(2);
//        Can be done the same way and commeting InjectMocks ...
//        Player player = new Player(dice, 3);

        assertFalse(player.play());
    }

    @Test
    public void winsWhenDiceNumberIsBigger() {
        when(dice.roll()).thenReturn(4);
        assertTrue(player.play());
    }
}