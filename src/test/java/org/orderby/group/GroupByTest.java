package org.orderby.group;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.orderby.group.GroupBy;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class GroupByTest {

    @Test
    public void testConvertingOneElementListToAMap() throws Exception {
        assertEquals(ImmutableMap.of("one", ImmutableList.of(1)), GroupBy.toMap(
                ImmutableList.of(1), (v) -> "one"));
    }
    
    @Test
    public void testConvertingTwoElementListToAMap() throws Exception {
        assertEquals(ImmutableMap.of("one", ImmutableList.of(1, 2)), GroupBy.toMap(
                ImmutableList.of(1, 2), (v) -> "one"));
    }
    
    @Test
    public void testConvertingListWithTwoKeysToAMap() throws Exception {
        assertEquals(ImmutableMap.of("odd", ImmutableList.of(1, 3), "even", ImmutableList.of(2, 4)), 
                     GroupBy.toMap(ImmutableList.of(1, 2, 3, 4), (v) -> v % 2 == 0 ? "even" : "odd"));
    }
    
}
