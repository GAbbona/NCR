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
public class FifthTest {
    
    public FifthTest() {
    }
    
     private final Calculate calc = new Calculate();

    @Test
    public void testCalculateNominationCuantity1() throws FileNotFoundException {
        System.out.println("testCalculateNominationCuantity 1");
        double expResult = 1440.0;
        double result = calc.getChange(1432d, calc.calculateDenominationCuantity(1432d));
        assertEquals(expResult, result);
    }

    //This test won't pass with config denomination cuantity set to 0
    @Test
    public void testCalculateNominationCuantity2() throws FileNotFoundException {
        System.out.println("testCalculateNominationCuantity 2");
        double expResult = 125.0;
        double result = calc.getChange(124d, calc.calculateDenominationCuantity(124d));
        assertEquals(expResult, result);
    }

    @Test
    public void testCalculateNominationCuantity3() throws FileNotFoundException {
        System.out.println("testCalculateNominationCuantity 3");
        double expResult = 100.0;
        double result = calc.getChange(94d, calc.calculateDenominationCuantity(94d));
        assertEquals(expResult, result);

    }
    
}
