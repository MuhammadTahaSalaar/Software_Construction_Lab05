package twitter;

import static org.junit.Assert.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class ExtractTest {
    
    // Sample timestamps for testing
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "Hello @user1! Is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes @user2 #hype", d2);
    
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
    }

    @Test
    public void testGetMentionedUsersWithMention() {
        Set<String> expectedUsers = new HashSet<>(Arrays.asList("user1", "user2"));
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2));
        assertEquals("expected mentioned users", expectedUsers, mentionedUsers);
    }

    @Test
    public void testGetMentionedUsersNoMention() {
        Tweet tweet3 = new Tweet(3, "user3", "No mentions here!", Instant.now());
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
}
