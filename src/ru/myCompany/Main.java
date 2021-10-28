package ru.myCompany;

import ru.myCompany.myComplex.MyComplex;

public class Main {
    public static void main(String[] args) {
        MyComplex complex1 = new MyComplex(1.5, -2);
        System.out.println(complex1);
        System.out.println(complex1.magnitude());
        System.out.println(complex1.argument());
        System.out.println("==================");

        MyComplex complex2 = new MyComplex(-2, 15);
        System.out.println(complex2);
        System.out.println(complex2.magnitude());
        System.out.println(complex2.argument());
        System.out.println("==================");

        MyComplex complex3 = new MyComplex(-2, -10.5);
        System.out.println(complex3);
        System.out.println(complex3.magnitude());
        System.out.println(complex3.argument());
        System.out.println("==================");

        MyComplex complex4 = new MyComplex(0,5);
        System.out.println(complex4);
        System.out.println(complex4.magnitude());
        System.out.println(complex4.argument());
        System.out.println("==================");

        MyComplex complex5 = new MyComplex(0,-5);
        System.out.println(complex5);
        System.out.println(complex5.magnitude());
        System.out.println(complex5.argument());
        System.out.println("==================");

        complex3.add(complex4);
        System.out.println(complex3);
        System.out.println("==================");

        MyComplex complex6 = complex1.addNew(complex5);
        System.out.println(complex6);
        System.out.println("==================");

        complex4.subtract(complex6);
        System.out.println(complex4);
        System.out.println("==================");

        MyComplex complex7 = complex1.subtractNew(complex4);
        System.out.println(complex7);
        System.out.println("==================");

        complex2.multiply(complex1);
        System.out.println(complex2);
        System.out.println("==================");

        complex2.divide(complex7);
        System.out.println(complex2);
        System.out.println("==================");

        MyComplex complex8 = complex4.conjugate();
        System.out.println(complex8);

    }
}
