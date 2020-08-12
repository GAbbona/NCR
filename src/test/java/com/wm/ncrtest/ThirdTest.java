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
public class ThirdTest {

    public ThirdTest() {
    }

    
    private final Calculate calc = new Calculate();

    @Test
    public void testCalculateDenomintionFromFile1() throws FileNotFoundException {
        System.out.println("CalculateDenomintionFromFile 1");
        double expResult = 140.0;
        double result = calc.getChange(132d, calc.calculateDenomination(132d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateDenomintionFromFile2() throws FileNotFoundException {
        System.out.println("CalculateDenomintionFromFile 2");
        double expResult = 125.0;
        double result = calc.getChange(124d, calc.calculateDenomination(124d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateDenomintionFromFile3() throws FileNotFoundException {
        System.out.println("CalculateDenomintionFromFile 3");
        double expResult = 100.0;
        double result = calc.getChange(94d, calc.calculateDenomination(194d));
        assertEquals(expResult, result);

    }

}
