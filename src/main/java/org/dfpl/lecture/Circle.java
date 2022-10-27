package org.dfpl.lecture;

import static java.lang.Math.PI;

public class Circle {
    double x, y, r;

    Circle(double _x,double _y, double _r){
        x = _x;
        y = _y;
        r = _r;
    }

    double getArea() {
        return r * r * PI;
    }

}
