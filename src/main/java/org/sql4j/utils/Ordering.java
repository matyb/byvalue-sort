package org.sql4j.utils;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Order some pojo/dto/vo/vm/etc. by a value it contains.
 * 
 * @param <F> the value to sort by
 * @param <T> the thing you want to sort
 */
public class Ordering<F,T> implements Comparator<T> {
    
    public static final Order ASC = new Order(1);
    public static final Order DESC = new Order(-1);
    
    private final Order order;
    private final Extractor<F,T> extractor;
    private final Comparator<F> comparator;
    
    public Ordering(Function<T,F> extractor, Comparator<F> comparator){
        this(extractor, comparator, ASC);
    }
    public Ordering(final Function<T,F> extractor, Comparator<F> comparator, Order order){
        this(new Extractor<F,T>(){
            @Override public F extract(T thing) {
                return extractor.apply(thing);
            }
        }, comparator, order);
    }
    public Ordering(Extractor<F,T> extractor, Comparator<F> comparator){
        this(extractor, comparator, ASC);
    }
    public Ordering(Extractor<F,T> extractor, Comparator<F> comparator, Order order){
        this.extractor = extractor;
        this.comparator = comparator;
        this.order = order;
    }
    
    @Override
    public int compare(T o1, T o2) {
        return order.order * comparator.compare(extractor.extract(o1), extractor.extract(o2));
    }
    
    
    public static class Order {
        private int order;
        Order(int order){
            this.order = order;
        }
    }
    
    public static interface Extractor<F,T> {
        /**
         * Extract the value you want to sort by from the thing you want to sort.
         * @param thingToSort
         * @return valueToSortBy
         */
        F extract(T thing);
    }
    
}
