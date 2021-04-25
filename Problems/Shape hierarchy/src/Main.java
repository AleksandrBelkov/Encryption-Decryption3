abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {

    double size1;
    double size2;
    double size3;

    protected Triangle(double size1, double size2, double size3) {
        this.size1 = size1;
        this.size2 = size2;
        this.size3 = size3;
    }

    protected double getPerimeter() {
        return size1 + size3 + size3;
    }

    protected double getArea() {
        double p;

        p = (size1 + size3 + size3) / 2;

        return Math.sqrt(p * (p - size1) * (p - size2) * (p - size3));
    }

}

class Rectangle extends Shape {

    double side1;
    double side2;

    protected Rectangle(double side1, double side2) {
        this.side1 = side1;
        this.side2 = side2;
    }

    protected double getPerimeter() {
        return (side1 + side2) * 2;
    }

    protected double getArea() {
        return side1 * side2;
    }

}

class Circle extends Shape {

    double radius;

    protected Circle(double radius) {
        this.radius = radius;

    }
    protected double getPerimeter() {
        return 2 * Math.PI * radius;
    }
    protected double getArea() {
        return Math.PI * radius * radius;
    }
}