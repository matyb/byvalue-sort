package org.orderby.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderBy<T> {
    
    private final List<Comparator<T>> comparators;
    
    @SafeVarargs
    public OrderBy(Comparator<T>... comparators) {
        this.comparators = Arrays.asList(comparators);
    }

    public List<T> sort(Collection<T> unsorted) {
        List<T> copy = new ArrayList<T>(unsorted);
        Collections.sort(copy, new Comparator<T>(){
            @Override
            public int compare(T o1, T o2) {
                return compare(o1, o2, 0);
            }
            private int compare(T o1, T o2, int i) {
                if(comparators.size() <= i){
                    return 0;
                }
                int comparison = comparators.get(i).compare(o1, o2);
                if(comparison == 0){
                    comparison = compare(o1, o2, ++i);
                }
                return comparison;
            }
        });
        return copy;
    }
    
}
