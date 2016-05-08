/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alysson.myrango.teste;

import java.text.DecimalFormat;

/**
 *
 * @author EU
 */
public class TesteValores {
    
    public static void main(String[] args) {
        double a=1;
        double b=12;
        double c = (a/b);
        double d = c*100;
        int e = (int) d;
        //b = 1/2;
        //int c = (int)b;
        //DecimalFormat df = new DecimalFormat("0.00");
        //String num = df.format(c);
        //double d = Double.valueOf(num);
        System.out.println("Valor: "+e);
    }
    
}
