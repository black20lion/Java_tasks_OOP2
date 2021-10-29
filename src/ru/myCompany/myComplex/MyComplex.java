package ru.myCompany.myComplex;

import java.util.Objects;

import static java.lang.Math.*;

public class MyComplex {
    private double real = 0.0;
    private double imag = 0.0;

    public MyComplex() {

    }

    public MyComplex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    public void setValue(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override
    public String toString() {
        char signOfImag = '+';
        if (imag < 0) signOfImag = '-';
        return "(" + real + signOfImag + abs(imag) + "i)";
    }

    public boolean isReal() {
        if (real == 0)
            return false;
        else
            return true;
    }

    public boolean isImaginary() {
        if (imag == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyComplex complex = (MyComplex) o;

        if (Double.compare(complex.real, real) != 0) return false;
        return Double.compare(complex.imag, imag) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(real);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imag);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public double magnitude() {
        return sqrt(pow(real, 2) + pow(imag, 2));
    }

    public double argument() throws IllegalArgumentException {
        if (real == 0.0 && imag == 0.0) throw new IllegalArgumentException("Argument of zero is undefined");
        else if (real > 0.0)
            return Math.atan(imag / real);
        else if (real < 0.0 && imag >= 0.0)
            return Math.PI + atan(imag / real);
        else if (real < 0.0 && imag < 0.0)
            return -Math.PI + atan(imag / real);
        else if (real == 0.0 && imag > 0.0)
            return Math.PI / 2;
        else
            return -Math.PI / 2;
    }

    public MyComplex add(MyComplex right) {
        this.real += right.real;
        this.imag += right.imag;
        return this;
    }

    public MyComplex addNew(MyComplex right) {
        MyComplex result = new MyComplex();
        result.real = this.real + right.real;
        result.imag = this.imag + right.imag;
        return result;
    }

    public MyComplex subtract(MyComplex right) {
        this.real -= right.real;
        this.imag -= right.imag;
        return this;
    }

    public MyComplex subtractNew(MyComplex right) {
        MyComplex result = new MyComplex();
        result.real = this.real - right.real;
        result.imag = this.imag - right.imag;
        return result;
    }

    public MyComplex multiply(MyComplex right) {
        double startReal = this.real;
        this.real = this.real * right.real - this.imag * right.imag;
        this.imag = this.imag * right.real + startReal * right.imag;
        return this;
    }

    public MyComplex divide(MyComplex right) throws IllegalArgumentException {
        if (right.real == 0.0 && right.imag == 0.0) throw new IllegalArgumentException("Division by zero is illegal");
        double startReal = this.real;
        this.real = (this.real * right.real + this.imag * right.imag) / (Math.pow(right.real, 2) + Math.pow(right.imag, 2));
        this.imag = (this.imag * right.real - startReal * right.imag) / (Math.pow(right.real, 2) + Math.pow(right.imag, 2));
        return this;
    }

    public MyComplex conjugate() {
        return new MyComplex(this.real, -this.imag);
    }

}
