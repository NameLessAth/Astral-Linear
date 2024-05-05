package com.mycompany.app;

import static org.junit.Assert.assertTrue;

// import com.mpatric.mp3agic.*;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        IWantToTestThis obj = new IWantToTestThis();
        obj.setx1();
        assertTrue( obj.getx() == 1 );
        obj.setx2();
        assertTrue( obj.getx() == 2 );
    }

    @Test
    public void shouldAnswerWithTrueAswell()
    {
        IWantToTestThis obj = new IWantToTestThis();

        obj.setx2();
        assertTrue(obj.getx() != 1);
    }
}
