package com.edsonsuarez.test2;

import javax.swing.*;

public class TestNumber {
    public static void main(String[] args) {
        int number = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número"));
        int lentNumber = String.valueOf(number).length();//,2 ,1 // 24
        String[] nameNumber1 = {"Uno","Dos","Tres","Cuatro","Cinco","Seis","Siete","Ocho","Nueve"};
        String[] nameNumber2 = {"Diez y ","Veinte y","Treinta y","Cuarenta y ","Cincuenta y","Sesenta y","Sieteita y","Ochointa y","Noventa y"};
        String[] numero = {"1","2","3","4","5","6","7","8","9","0"};
        String[][] dig = new String[5][2];
        dig[0] = numero;
        dig[1] = nameNumber1;
        dig[2] = nameNumber2;

        int n1 = Math.round(number/10);
        int n2 = number%10;

        System.out.println(dig[lentNumber][n1-1] +""+dig[lentNumber-1][n2-1]);

        /*for (int i = 0; i < dig.length; i++) {
            String numero = dig[lentNumber];
        }*/
    }
}
