package org.example.itkissues;

import java.util.ArrayList;
import java.util.List;

public class FilterService {

    public <T> List<T> filterMethod (T[] arr, Filter filter) {
        List<T> result = new ArrayList<>();
        for (T el : arr) {
            Object filtred = filter.apply(el);
            if (filtred != null) {
                result.add(el);
            }
        }
        return result;
    }
}
