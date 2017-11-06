package org.byvalue.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListIndexComparator<F> implements Comparator<F> {
    
    private final List<F> sequence;
    
    public ListIndexComparator(List<F> sequence){
        this.sequence = Collections.unmodifiableList(sequence);
    }
    
    @Override
    public int compare(F o1, F o2) {
        int left = sequence.indexOf(o1);
        int right = sequence.indexOf(o2);
        if(left == -1 && right != -1){
            return 1;
        }
        if(right == -1 && left != -1){
            return -1;
        }
        return left - right;
    }
}
