package ru.myCompany.myPolynomial;

import java.util.Arrays;

public class MyPolynomial {
    private double[] coeffs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPolynomial that = (MyPolynomial) o;

        return Arrays.equals(coeffs, that.coeffs);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coeffs);
    }

    public MyPolynomial(double... coeffs) {
        this.coeffs = coeffs;
    }

    public int getDegree() {
        int currentMax = 0;
        for (int i = 0; i <= coeffs.length - 1; i++) {
            if (coeffs[i] != 0)
                currentMax = i;
        }
        return currentMax;
    }

    @Override
    public String toString() {
        if (coeffs.length == 0)
            return "Polynomial is epmpty";
        String result = "";
        for (int i = coeffs.length - 1; i >= 0; i--) {

            if (coeffs[i] != 1 && coeffs[i] != -1 && coeffs[i] != 0)
                result += coeffs[i];
            else if (coeffs[i] == -1 && i != 0)
                result += "-";
            else if (coeffs[i] == -1 && i == 0)
                result += coeffs[i];
            else if (coeffs[i] == 1 && i == 0)
                result += coeffs[i];

            if (i > 1 && coeffs[i] != 0) {
                result += "x^" + i;
                if (coeffs[i - 1] > 0)
                    result += "+";
            } else if (i > 0 && coeffs[i] != 0) {
                result += "x";
                if (coeffs[i - 1] > 0)
                    result += "+";
            }
        }
        return result;
    }

    public double evaluate(double x) throws RuntimeException {
        if (coeffs.length == 0) throw new RuntimeException("Polynomial is epmpty");
        double result = 0.0;
        for (int i = coeffs.length - 1; i >= 1; i--) {
            result += Math.pow(x, i) * coeffs[i];
        }
        result += coeffs[0];
        return result;
    }

    public MyPolynomial add(MyPolynomial right) {
        MyPolynomial result;
        if (this.coeffs.length >= right.coeffs.length) {
            double[] resultArray = this.coeffs.clone();
            result = new MyPolynomial(resultArray);
            for (int i = 0; i < right.coeffs.length; i++) {
                result.coeffs[i] += right.coeffs[i];
            }
        } else {
            double[] resultArray = right.coeffs.clone();
            result = new MyPolynomial(resultArray);
            for (int i = 0; i < this.coeffs.length; i++) {
                result.coeffs[i] += this.coeffs[i];
            }
        }
        return result;
    }

    public MyPolynomial multiply(MyPolynomial right) {
        MyPolynomial result = new MyPolynomial();
        result.coeffs = new double[this.coeffs.length + right.coeffs.length - 1];

        // Init the array by zeros to avoid nulls
        for (int i = 0; i < result.coeffs.length; i++) {
            result.coeffs[i] = 0;
        }

        for (int i = this.coeffs.length - 1; i >= 0; i--) {
            for (int j = right.coeffs.length - 1; j >= 0; j--) {
                if (this.coeffs[i] != 0 && right.coeffs[j] != 0) {
                    result.coeffs[i + j] += this.coeffs[i] * right.coeffs[j];
                }
            }
        }

        return result;
    }
}
