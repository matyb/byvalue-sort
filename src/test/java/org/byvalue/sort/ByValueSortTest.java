package org.byvalue.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.byvalue.sort.ByValueSort;
import org.byvalue.sort.OrderBy.Extractor;
import org.junit.Test;

public class ByValueSortTest {

    public static final List<TypeOfRequestEnum> REQUEST_ORDER = Collections.unmodifiableList(Arrays.asList(
            TypeOfRequestEnum.FIRST,TypeOfRequestEnum.SECOND,TypeOfRequestEnum.THIRD));
    
    public static final List<TypeOfPaymentEnum> PAYMENT_ORDER = Collections.unmodifiableList(Arrays.asList(
            TypeOfPaymentEnum.CASH,TypeOfPaymentEnum.CHECK,TypeOfPaymentEnum.BOOST_MOBILE));
    
    @Test
    public void sortsByListAsc() throws Exception {
        Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
            @Override public TypeOfRequestEnum extract(ThingImSorting thing){
                return thing.getTypeOfRequest();
            }
        };

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.ASC));
        
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

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.ASC));
        
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

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.DESC));
        
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

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.ASC),
                                                               new OrderBy<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), OrderBy.ASC));
        
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

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.ASC),
                                                               new OrderBy<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), OrderBy.DESC));
        
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

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.DESC),
                                                               new OrderBy<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), OrderBy.ASC));
        
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

        ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.DESC),
                                                               new OrderBy<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), OrderBy.DESC));

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

        private ByValueSortTest getOuterType() {
            return ByValueSortTest.this;
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
