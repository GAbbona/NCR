/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wm.ncrtest;

import com.wm.service.Calculate;
import java.util.Arrays;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author WM
 */
public class SecondTest {

    public SecondTest() {
    }

    private final List<Double> nominations = Arrays.asList(10d, 25d, 50d, 100d);
    private final Calculate calc = new Calculate();

    @Test
    public void testCalculateDenomintion1() {
        System.out.println("calculateDenomintion 1");
        double expResult = 140.0;
        double result = calc.getChange(132d, calc.calculateDenomination(nominations, 132d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateDenomintion2() {
        System.out.println("calculateDenomintion 2");
        double expResult = 125.0;
        double result = calc.getChange(124d, calc.calculateDenomination(nominations, 124d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateDenomintion3() {
        System.out.println("calculateDenomintion 3");
        double expResult = 100.0;
        double result = calc.getChange(94d, calc.calculateDenomination(nominations, 94d));
        assertEquals(expResult, result);

    }

}
