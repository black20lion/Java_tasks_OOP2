package ru.myCompany;

import ru.myCompany.myPolynomial.MyPolynomial;
import ru.myCompany.ball.Ball;
import ru.myCompany.ball.Container;
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

        MyComplex complex4 = new MyComplex(0, 5);
        System.out.println(complex4);
        System.out.println(complex4.magnitude());
        System.out.println(complex4.argument());
        System.out.println("==================");

        MyComplex complex5 = new MyComplex(0, -5);
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
        System.out.println("==================");

        MyPolynomial polynomial1 = new MyPolynomial(-1, 2, -1, -5, 0, -18, 20, 12);
        System.out.println(polynomial1.getDegree());
        System.out.println(polynomial1);
        System.out.println(polynomial1.evaluate(2));
        System.out.println("==================");

        MyPolynomial polynomial2 = new MyPolynomial();
        System.out.println(polynomial2);
        System.out.println("==================");

        MyPolynomial polynomial3 = new MyPolynomial(-5, 6, 12);
        MyPolynomial polynomial4 = new MyPolynomial(2, 5, 10, 7);
        MyPolynomial polynomial5 = polynomial3.add(polynomial4);
        System.out.println(polynomial5);
        System.out.println(polynomial4);
        System.out.println("==================");

        MyPolynomial polynomial6 = polynomial3.multiply(polynomial4);
        System.out.println(polynomial6);
        System.out.println("==================");

        Ball ballNotExist = new Ball(2, 1, 3, 10, -195);
        System.out.println(ballNotExist);

        Ball ball1 = new Ball(2, 1, 3, 10, -135);
        System.out.println(ball1);

        Ball ball2 = new Ball(2, 1, 1, 10, -45);

        Container container1 = new Container(-2, -2, 7, 5);
        System.out.println(container1);

        System.out.println(container1.collides(ball1));
        System.out.println(container1.collides(ball2));

        System.out.println("==================");

        Container container2 = new Container(-10, -7, 15, 19);
        Ball ball3 = new Ball(0, 0, 5, 10, -45);
        System.out.println(ball3);
        System.out.println(container2.collides(ball3));
        ball3.move();
        System.out.println(ball3);
        System.out.println(container2.collides(ball3));
        System.out.println("==================");


        Container containerTrial = new Container(-9, -9, 27, 22);
        Ball ballTrial = new Ball(9, 2, 3, 5, -70);

        System.out.println(ballTrial);
        moveWithReflection(containerTrial, ballTrial);
        System.out.println(containerTrial.collides(ballTrial));

        System.out.println(ballTrial);
        moveWithReflection(containerTrial, ballTrial);
        System.out.println(containerTrial.collides(ballTrial));

        System.out.println(ballTrial);
        moveWithReflection(containerTrial, ballTrial);
        System.out.println(containerTrial.collides(ballTrial));

        System.out.println(ballTrial);
        moveWithReflection(containerTrial, ballTrial);
        System.out.println(containerTrial.collides(ballTrial));

        System.out.println(ballTrial);
        moveWithReflection(containerTrial, ballTrial);
        System.out.println(containerTrial.collides(ballTrial));
    }

    //Let's use reflectHorizontal() and reflectVertical() methods to avoid collision
    public static void moveWithReflection(Container container, Ball ball) throws IllegalArgumentException {
        if (container.collides(ball))
            throw new IllegalArgumentException("The ball is out of the container");

        // if move freely, no constrains
        if (ball.getX() + ball.getXDelta() + ball.getRadius() < container.getX() + container.getWidth() &&
                ball.getX() + ball.getXDelta() - ball.getRadius() > container.getX() &&
                ball.getY() + ball.getYDelta() + ball.getRadius() < container.getY() + container.getHeight() &&
                ball.getY() + ball.getYDelta() - ball.getRadius() > container.getY()) {
            ball.move();
        }

        // if collides during move, stop on bound and reflect depending on direction of moving
        else if (ball.getXDelta() > 0 && ball.getYDelta() > 0) {
            float distanceToRightBound = (container.getX() + container.getWidth()) - (ball.getX() + ball.getRadius());
            float distanceToBottomBound = (container.getY() + container.getHeight()) - (ball.getY() + ball.getRadius());
            float timeRightCollision = distanceToRightBound / ball.getXDelta();
            float timeBottomCollision = distanceToBottomBound / ball.getYDelta();

            if (timeRightCollision < timeBottomCollision) {
                ball.setX(container.getX() + container.getWidth() - ball.getRadius());
                ball.setY(ball.getY() + ball.getYDelta() * timeRightCollision);
                ball.reflectHorizontal();

            } else if (timeRightCollision > timeBottomCollision) {
                ball.setY(container.getY() + container.getHeight() - ball.getRadius());
                ball.setX(ball.getX() + ball.getXDelta() * timeBottomCollision);
                ball.reflectVertical();
            } else {
                ball.setX(container.getX() + container.getWidth() - ball.getRadius());
                ball.setY(container.getY() + container.getHeight() - ball.getRadius());
                ball.reflectHorizontal();
                ball.reflectVertical();
            }
        } else if (ball.getXDelta() < 0 && ball.getYDelta() > 0) {
            float distanceToLeftBound = ball.getX() - ball.getRadius() - container.getX();
            float distanceToBottomBound = (container.getY() + container.getHeight()) - (ball.getY() + ball.getRadius());
            float timeLeftCollision = distanceToLeftBound / Math.abs(ball.getXDelta());
            float timeBottomCollision = distanceToBottomBound / ball.getYDelta();

            if (timeLeftCollision < timeBottomCollision) {
                ball.setX(container.getX() - ball.getRadius());
                ball.setY(ball.getY() + ball.getYDelta() * timeLeftCollision);
                ball.reflectHorizontal();

            } else if (timeLeftCollision > timeBottomCollision) {
                ball.setY(container.getY() + container.getHeight() - ball.getRadius());
                ball.setX(ball.getX() + ball.getXDelta() * timeBottomCollision);
                ball.reflectVertical();
            } else {
                ball.setX(container.getX() - ball.getRadius());
                ball.setY(container.getY() + container.getHeight() - ball.getRadius());
                ball.reflectHorizontal();
                ball.reflectVertical();
            }
        } else if (ball.getXDelta() < 0 && ball.getYDelta() < 0) {
            float distanceToLeftBound = ball.getX() - ball.getRadius() - container.getX();
            float distanceToTopBound = ball.getY() - ball.getRadius() - container.getY();
            float timeLeftCollision = distanceToLeftBound / Math.abs(ball.getXDelta());
            float timeTopCollision = distanceToTopBound / Math.abs(ball.getYDelta());

            if (timeLeftCollision < timeTopCollision) {
                ball.setX(container.getX() - ball.getRadius());
                ball.setY(ball.getY() + ball.getYDelta() * timeLeftCollision);
                ball.reflectHorizontal();

            } else if (timeLeftCollision > timeTopCollision) {
                ball.setY(container.getY() + ball.getRadius());
                ball.setX(ball.getX() + ball.getXDelta() * timeTopCollision);
                ball.reflectVertical();
            } else {
                ball.setX(container.getX() - ball.getRadius());
                ball.setY(container.getY() + ball.getRadius());
                ball.reflectHorizontal();
                ball.reflectVertical();
            }
        } else if (ball.getXDelta() > 0 && ball.getYDelta() < 0) {
            float distanceToRightBound = (container.getX() + container.getWidth()) - (ball.getX() + ball.getRadius());
            float distanceToTopBound = ball.getY() - ball.getRadius() - container.getY();
            float timeRightCollision = distanceToRightBound / ball.getXDelta();
            float timeTopCollision = distanceToTopBound / Math.abs(ball.getYDelta());

            if (timeRightCollision < timeTopCollision) {
                ball.setX(container.getX() + container.getWidth() - ball.getRadius());
                ball.setY(ball.getY() + ball.getYDelta() * timeRightCollision);
                ball.reflectHorizontal();
            } else if (timeRightCollision > timeTopCollision) {
                ball.setY(container.getY() + ball.getRadius());
                ball.setX(ball.getX() + ball.getXDelta() * timeTopCollision);
                ball.reflectVertical();
            } else {
                ball.setX(container.getX() + container.getWidth() - ball.getRadius());
                ball.setY(container.getY() + ball.getRadius());
                ball.reflectHorizontal();
                ball.reflectVertical();
            }
        } else if (ball.getXDelta() > 0 && ball.getYDelta() == 0) {
            ball.setX(container.getX() + container.getWidth() - ball.getRadius());
            ball.reflectHorizontal();
        } else if (ball.getXDelta() < 0 && ball.getYDelta() == 0) {
            ball.setX(container.getX() - ball.getRadius());
            ball.reflectHorizontal();
        } else if (ball.getXDelta() == 0 && ball.getYDelta() > 0) {
            ball.setY(container.getY() + container.getHeight() - ball.getRadius());
            ball.reflectVertical();
        } else if (ball.getXDelta() == 0 && ball.getYDelta() < 0) {
            ball.setY(container.getY() + ball.getRadius());
            ball.reflectVertical();
        }
    }
}
