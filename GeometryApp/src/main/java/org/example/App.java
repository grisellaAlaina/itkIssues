package org.example;

public class App 
{
    public static void main( String[] args )
    {
        Circle circle = new Circle(5);
        System.out.println("Area of circle: " + circle.calculateArea());
        System.out.println("Perimeter of circle: " + circle.calculatePerimeter());

        Rectangle rectangle = new Rectangle(5, 6);
        System.out.println("Area of rectangle: " + rectangle.calculateArea());
        System.out.println("Perimeter of rectangle: " + rectangle.calculatePerimeter());

        Triangle triangle = new Triangle(5, 6, 7, 8);
        System.out.println("Area of triangle: " + triangle.calculateArea());
        System.out.println("Perimeter of triangle: " + triangle.calculatePerimeter());
    }
}
