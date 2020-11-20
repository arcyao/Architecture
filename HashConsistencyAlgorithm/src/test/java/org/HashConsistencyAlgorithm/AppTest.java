package org.HashConsistencyAlgorithm;

import static org.junit.Assert.assertTrue;

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
    public void shouldAnswerWithTrue() {
        for (int i = 0;i < 5; i++) {
            App.main(new String[]{});
        }
        assertTrue( true );
    }

}
