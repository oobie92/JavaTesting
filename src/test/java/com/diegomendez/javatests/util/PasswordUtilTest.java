package com.diegomendez.javatests.util;

import org.junit.Test;

import static com.diegomendez.javatests.util.PasswordUtil.SecurityLevel.*;
import static org.junit.Assert.*;

public class PasswordUtilTest {

    @Test
    public void weakWhenHasLessThan8Characters() {
        assertEquals(WEAK, PasswordUtil.accessPassword("1234567"));
    }

    @Test
    public void weakWhenHasOnlyLetters() {
        assertEquals(WEAK, PasswordUtil.accessPassword("abcdef"));
    }

    @Test
    public void mediumWhenHasNumbersAndLetters() {
        assertEquals(MEDIUM, PasswordUtil.accessPassword("abcdef1234"));
    }

    @Test
    public void strongWhenHasNumbersLettersAndSymbols() {
        assertEquals(STRONG, PasswordUtil.accessPassword("abcdef1234#!"));
    }
}