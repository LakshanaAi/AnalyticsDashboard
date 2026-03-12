import java.util.*;

/**
 * UseCase2TwitterTrendingTracker
 * Tracks trending hashtags similar to Twitter.
 */

public class AnalyticsDashboard {

    // hashtag -> count
    private HashMap<String, Integer> hashtagCount = new HashMap<>();

    public void processTweet(String tweet) {

        String[] words = tweet.split(" ");

        for (String word : words) {

            if (word.startsWith("#")) {

                hashtagCount.put(word,
                        hashtagCount.getOrDefault(word, 0) + 1);
            }
        }
    }

    public void displayTrending() {

        System.out.println("\n===== TRENDING HASHTAGS =====");

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(hashtagCount.entrySet());

        int rank = 1;

        while (!pq.isEmpty() && rank <= 10) {

            Map.Entry<String, Integer> entry = pq.poll();

            System.out.println(rank + ". " +
                    entry.getKey() + " - " +
                    entry.getValue() + " mentions");

            rank++;
        }
    }

    public static void main(String[] args) {

        AnalyticsDashboard tracker = new AnalyticsDashboard();

        // Simulated tweets
        tracker.processTweet("Breaking news in AI #AI #Technology");
        tracker.processTweet("Football finals tonight #Sports #Football");
        tracker.processTweet("New smartphone launch #Technology");
        tracker.processTweet("AI transforming industries #AI");
        tracker.processTweet("Amazing match today #Sports");

        tracker.displayTrending();
    }
}
