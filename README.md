# By Value Sort
[![Build Status](https://travis-ci.org/matyb/byvalue-sort.png?branch=master)](https://travis-ci.org/matyb/byvalue-sort)

Equivalent functionality to SQL's ORDER BY, but for java. This isn't very performant, if you want that - use a database.
```java
// sort by 2 'columns` (values) desc w/ static definition of value ordering
public static final List<TypeOfRequestEnum> REQUEST_ORDER = Collections.unmodifiableList(Arrays.asList(
            TypeOfRequestEnum.FIRST,TypeOfRequestEnum.SECOND,TypeOfRequestEnum.THIRD));
public static final List<TypeOfPaymentEnum> PAYMENT_ORDER = Collections.unmodifiableList(Arrays.asList(
            TypeOfPaymentEnum.CASH,TypeOfPaymentEnum.CHECK,TypeOfPaymentEnum.BOOST_MOBILE));

final Extractor<TypeOfRequestEnum, ThingImSorting> requestExtractor = new Extractor<TypeOfRequestEnum, ThingImSorting>(){
    @Override public TypeOfRequestEnum extract(ThingImSorting thing){
        return thing.getTypeOfRequest();
    }
};
final Extractor<TypeOfPaymentEnum, ThingImSorting> paymentExtractor = new Extractor<TypeOfPaymentEnum, ThingImSorting>(){
    @Override public TypeOfPaymentEnum extract(ThingImSorting thing){
        return thing.getTypeOfPayment();
    }
};

// in some method
ByValueSort<ThingImSorting> sorter = new ByValueSort<>(new OrderBy<>(requestExtractor, new ListIndexComparator<>(REQUEST_ORDER), OrderBy.DESC),
                                                       new OrderBy<>(paymentExtractor, new ListIndexComparator<>(PAYMENT_ORDER), OrderBy.DESC));
List<ThingImSorting> sorted = sorter.sort(unsorted);
```
