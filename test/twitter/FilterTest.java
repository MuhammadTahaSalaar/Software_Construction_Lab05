/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FilterTest {

    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "This is a tweet", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "Another tweet here", d2);

    @Test
    public void testWrittenBy() {
        List<Tweet> result = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        assertEquals(1, result.size());
        assertTrue(result.contains(tweet1));
    }

    @Test
    public void testInTimespan() {
        Timespan timespan = new Timespan(Instant.parse("2016-02-17T09:00:00Z"), Instant.parse("2016-02-17T12:00:00Z"));
        List<Tweet> result = Filter.inTimespan(Arrays.asList(tweet1, tweet2), timespan);
        assertTrue(result.contains(tweet1));
        assertTrue(result.contains(tweet2));
    }

    @Test
    public void testContaining() {
        List<Tweet> result = Filter.containing(Arrays.asList(tweet1, tweet2), Arrays.asList("tweet"));
        assertFalse(result.isEmpty());
        assertTrue(result.contains(tweet1));
        assertTrue(result.contains(tweet2));
    }
}
