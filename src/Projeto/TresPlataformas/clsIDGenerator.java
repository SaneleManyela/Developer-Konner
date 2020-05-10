/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.util.Random;

/**
 *
 * @author Sanele
 */
public class clsIDGenerator {
    private static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String ALL_CHARS = CAPITAL_LETTERS + DIGITS;

    private static final Random RND = new Random();
    //A static method to insert characters randomly
    private static void mInsertAtRandomPosition(StringBuilder id, char character)
    {
        int randomPosition = RND.nextInt(id.length() + 1);
        id.insert(randomPosition, character);
    }
    //A static method that generates characters randomly
    private static char mGenerateChar(String availableChars)
    {
        int randomIndex = RND.nextInt(availableChars.length());
        char randomChar = availableChars.charAt(randomIndex);
        return randomChar;
    }
    //A method that returns an ID
    public StringBuilder mGetID()
    {
        StringBuilder id = new StringBuilder();

        //Generate four random capital letters
        for (int i = 1; i <= 4; i++)
        {
            char capitalLetters = mGenerateChar(CAPITAL_LETTERS);
            mInsertAtRandomPosition(id, capitalLetters);
        }

        //generate one random digit
        char digit = mGenerateChar(DIGITS);
        mInsertAtRandomPosition(id, digit);

        //Generate few random characters (between 0 and 4)
        int count = RND.nextInt(4);
        for (int i = 1; i <= count; i++)
        {
            char specialChar = mGenerateChar(ALL_CHARS);
            mInsertAtRandomPosition(id, specialChar);
        }
        return id;
    }
}