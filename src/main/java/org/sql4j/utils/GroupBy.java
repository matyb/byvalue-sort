package org.sql4j.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class GroupBy {

    public static <K,V> Map<K, List<V>> toMap(Collection<V> collection, Function<V,K> keyFn){
        Map<K,List<V>> map = new LinkedHashMap<>();
        for(V value : collection){
            K key = keyFn.apply(value);
            List<V> col = map.get(key);
            if(col == null){
                col = new ArrayList<>();
                map.put(key, col);
            }
            col.add(value);
        }
        return map;
    }
    
}
