package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {

    // Introduced bug: Doesn't correctly handle the case where the last tweet has the earliest timestamp
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty()) {
            throw new IllegalArgumentException("Tweet list cannot be empty");
        }

        Instant start = tweets.get(0).getTimestamp();
        Instant end = tweets.get(0).getTimestamp();
        
        // Bug: Skips the first tweet in iteration (starts from index 1)
        for (int i = 1; i < tweets.size(); i++) {
            Instant current = tweets.get(i).getTimestamp();
            if (current.isBefore(start)) {
                start = current;
            }
            if (current.isAfter(end)) {
                end = current;
            }
        }

        return new Timespan(start, end);
    }

    // Introduced bug: Doesn't correctly identify usernames containing non-alphanumeric characters
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> users = new HashSet<>();
        Pattern pattern = Pattern.compile("@(\\w+)");

        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                String username = matcher.group(1).toLowerCase();
                // Bug: Ignores mentions that contain underscores or special characters
                if (username.matches("[a-zA-Z0-9]+")) {
                    users.add(username);
                }
            }
        }

        return users;
    }
}
