/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wm.ncrtest;

import com.wm.service.Calculate;
import java.io.FileNotFoundException;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author WM
 */
public class FourthTest {

    public FourthTest() {
    }
    
     private final Calculate calc = new Calculate();

    @Test
    public void testCalculateDenomintionLevel1() throws FileNotFoundException {
        System.out.println("testCalculateDenomintionLevel 1");
        double expResult = 140.0;
        double result = calc.getChange(132d, calc.calculateDenominationLevel(132d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateDenomintionLevel2() throws FileNotFoundException {
        System.out.println("testCalculateDenomintionLevel 2");
        double expResult = 130.0;
        double result = calc.getChange(124d, calc.calculateDenominationLevel(124d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateDenomintionLevel3() throws FileNotFoundException {
        System.out.println("testCalculateDenomintionLevel 3");
        double expResult = 100.0;
        double result = calc.getChange(94d, calc.calculateDenominationLevel(94d));
        assertEquals(expResult, result);

    }

}
