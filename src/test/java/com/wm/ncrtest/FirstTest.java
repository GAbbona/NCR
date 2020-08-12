/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wm.ncrtest;

import com.wm.service.Calculate;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author WM
 */
public class FirstTest {

    public FirstTest() {
    }
    
    private final Calculate calc = new Calculate();

    @Test
    public void testGetChange1() {
        System.out.println("getChange 1");
        double expResult = 135.0;
        double result = calc.getChange(132d, 5d);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetChange2() {
        System.out.println("getChange 2");
        double expResult = 350.0;
        double result = calc.getChange(346d, 5d);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetChange3() {
        System.out.println("getChange 3");
        double expResult = 95.0;
        double result = calc.getChange(94d, 5d);
        assertEquals(expResult, result);
    }

}
