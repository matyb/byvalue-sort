package org.orderby.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;
import org.orderby.sort.Ordering.Extractor;

import com.google.common.collect.ImmutableList;

public class OrderByTest {

    public static final List<TypeOfRequestEnum> REQUEST_ORDER = ImmutableList.of(
            TypeOfRequestEnum.FIRST, TypeOfRequestEnum.SECOND, TypeOfRequestEnum.THIRD);
    
    public static final List<TypeOfPaymentEnum> PAYMENT_ORDER = ImmutableList.of(
            TypeOfPaymentEnum.CASH, TypeOfPaymentEnum.CHECK, TypeOfPaymentEnum.BOOST_MOBILE);
    
    @Test
    public void sortsByListAsc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.ASC));
        
        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.FIRST),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD));
        
        assertEquals(expected, sorter.sort(unsorted));
    }
    
    @Test
    public void supportsJava8Hofs() throws Exception {
        Function<ThingImSorting, TypeOfRequestEnum> fn = ThingImSorting::getTypeOfRequest;

        OrderBy<ThingImSorting> sorter = new OrderBy<>(
                new Ordering<>(fn, new ListIndexComparator<>(REQUEST_ORDER), Ordering.ASC));

        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST));

        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.FIRST),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD));

        assertEquals(expected, sorter.sort(unsorted));
    }

    @Test
    public void sortsUnfoundToEnd() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.ASC));
        
        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD),
                                                      new ThingImSorting(null),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.FIRST),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD),
                                                      new ThingImSorting(null));
        
        assertEquals(expected, sorter.sort(unsorted));
    }
    
    @Test
    public void sortsByListDesc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.DESC));
        
        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.FIRST),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST));

        assertEquals(expected, sorter.sort(unsorted));
    }

    @Test
    public void sortsByTwoListsAscAsc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };
        Extractor<TypeOfPaymentEnum, ThingImSorting> paymentExtractor = new Extractor<TypeOfPaymentEnum, ThingImSorting>(){
            @Override public TypeOfPaymentEnum extract(ThingImSorting thing){
                return thing.getTypeOfPayment();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.ASC),
                                                               new Ordering<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), Ordering.ASC));
        
        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH));
        
        assertEquals(expected, sorter.sort(unsorted));
    }
    
    @Test
    public void sortsByTwoListsAscDesc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };
        Extractor<TypeOfPaymentEnum, ThingImSorting> paymentExtractor = new Extractor<TypeOfPaymentEnum, ThingImSorting>(){
            @Override public TypeOfPaymentEnum extract(ThingImSorting thing){
                return thing.getTypeOfPayment();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.ASC),
                                                               new Ordering<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), Ordering.DESC));
        
        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH));
        
        assertEquals(expected, sorter.sort(unsorted));
    }
    
    @Test
    public void sortsByTwoListsDescAsc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };
        Extractor<TypeOfPaymentEnum, ThingImSorting> paymentExtractor = new Extractor<TypeOfPaymentEnum, ThingImSorting>(){
            @Override public TypeOfPaymentEnum extract(ThingImSorting thing){
                return thing.getTypeOfPayment();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.DESC),
                                                               new Ordering<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), Ordering.ASC));
        
        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE));
        
        assertEquals(expected, sorter.sort(unsorted));
    }
    
    @Test
    public void sortsByTwoListsDescDesc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };
        Extractor<TypeOfPaymentEnum, ThingImSorting> paymentExtractor = new Extractor<TypeOfPaymentEnum, ThingImSorting>(){
            @Override public TypeOfPaymentEnum extract(ThingImSorting thing){
                return thing.getTypeOfPayment();
            }
        };

        OrderBy<ThingImSorting> sorter = new OrderBy<>(new Ordering<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), Ordering.DESC),
                                                               new Ordering<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), Ordering.DESC));

        List<ThingImSorting> unsorted = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK));
        
        List<ThingImSorting> expected = Arrays.asList(new ThingImSorting(TypeOfRequestEnum.THIRD, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.BOOST_MOBILE),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CHECK),
                                                      new ThingImSorting(TypeOfRequestEnum.SECOND, TypeOfPaymentEnum.CASH),
                                                      new ThingImSorting(TypeOfRequestEnum.FIRST, TypeOfPaymentEnum.BOOST_MOBILE));
        
        assertEquals(expected, sorter.sort(unsorted));
    }
    
    class ThingImSorting {
        TypeOfRequestEnum typeOfRequest;
        TypeOfPaymentEnum typeOfPayment;

        ThingImSorting(TypeOfRequestEnum typeOfRequest){
            this(typeOfRequest, null);
        }
        
        ThingImSorting(TypeOfRequestEnum typeOfRequest, TypeOfPaymentEnum typeOfPayment){
            this.typeOfRequest = typeOfRequest;
            this.typeOfPayment = typeOfPayment;
        }
        
        public TypeOfRequestEnum getTypeOfRequest() {
            return typeOfRequest;
        }

        public TypeOfPaymentEnum getTypeOfPayment() {
            return typeOfPayment;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((typeOfPayment == null) ? 0 : typeOfPayment.hashCode());
            result = prime * result + ((typeOfRequest == null) ? 0 : typeOfRequest.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ThingImSorting other = (ThingImSorting) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (typeOfPayment != other.typeOfPayment)
                return false;
            if (typeOfRequest != other.typeOfRequest)
                return false;
            return true;
        }

        private OrderByTest getOuterType() {
            return OrderByTest.this;
        }

        @Override
        public String toString() {
            return "ThingImSorting [typeOfRequest=" + typeOfRequest + ", typeOfPayment=" + typeOfPayment + "]";
        }        
        
    }

    public enum TypeOfRequestEnum {
        FIRST, SECOND, THIRD
    }
    
    public enum TypeOfPaymentEnum {
        CASH, CHECK, BOOST_MOBILE
    }
    
}
