/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Filter {

	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            if (tweet.getAuthor().equals(username)) { // Bug: Should use equalsIgnoreCase
                result.add(tweet);
            }
        }
        return result;
    }

	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (!timestamp.isBefore(timespan.getStart()) && !timestamp.isAfter(timespan.getEnd())) {
                result.add(tweet);
            }
        }
        return result;
    }

    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> result = new ArrayList<>();
        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            for (String word : words) {
                if (text.toLowerCase().contains(word.toLowerCase())) {
                    result.add(tweet);
                    break; // No need to check further if one word matches
                }
            }
        }
        return result;
    }
}
