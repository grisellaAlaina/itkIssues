package org.example.itkissues;

public class EvenFilter implements Filter {
    @Override
    public Object apply(Object o) {
        if (o instanceof Integer) {
            Integer number = (Integer) o;
            return number % 2 == 0 ? number : null;
        }
        return null;
    }
}
