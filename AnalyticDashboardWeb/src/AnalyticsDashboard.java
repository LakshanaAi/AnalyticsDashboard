import java.util.*;

/**
 UC2-TwitterTrendingTracker
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

 * UseCase1GoogleAnalyticsDashboard
 * Simulates a real-time analytics dashboard similar to Google Analytics.
 */

class AnalyticsEvent {
    String url;
    String userId;
    String source;

    public AnalyticsEvent(String url, String userId, String source) {
        this.url = url;
        this.userId = userId;
        this.source = source;
    }
}

public class AnalyticsDashboard {

    // pageUrl -> visit count
    private HashMap<String, Integer> pageViews = new HashMap<>();

    // pageUrl -> set of unique visitors
    private HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();

    // traffic source -> count
    private HashMap<String, Integer> trafficSources = new HashMap<>();

    public void processEvent(AnalyticsEvent event) {

        pageViews.put(event.url, pageViews.getOrDefault(event.url, 0) + 1);

        uniqueVisitors.putIfAbsent(event.url, new HashSet<>());
        uniqueVisitors.get(event.url).add(event.userId);

        trafficSources.put(event.source,
                trafficSources.getOrDefault(event.source, 0) + 1);
    }

    public void displayDashboard() {

        System.out.println("\n===== REAL-TIME ANALYTICS DASHBOARD =====");
 main

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

 UC2-TwitterTrendingTracker
        pq.addAll(hashtagCount.entrySet());

        pq.addAll(pageViews.entrySet());

        System.out.println("\nTop Pages:");
 main

        int rank = 1;

        while (!pq.isEmpty() && rank <= 10) {

            Map.Entry<String, Integer> entry = pq.poll();
 UC2-TwitterTrendingTracker

            System.out.println(rank + ". " +
                    entry.getKey() + " - " +
                    entry.getValue() + " mentions");

            rank++;
        }

            String page = entry.getKey();
            int views = entry.getValue();
            int unique = uniqueVisitors.get(page).size();

            System.out.println(rank + ". " + page + " - "
                    + views + " views (" + unique + " unique visitors)");

            rank++;
        }

        System.out.println("\nTraffic Sources:");

        for (String source : trafficSources.keySet()) {
            System.out.println(source + " → " + trafficSources.get(source) + " visits");
        }
 main
    }

    public static void main(String[] args) {

 UC2-TwitterTrendingTracker
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

        AnalyticsDashboard dashboard = new AnalyticsDashboard();

        dashboard.processEvent(new AnalyticsEvent("/article/breaking-news", "user_101", "google"));
        dashboard.processEvent(new AnalyticsEvent("/article/breaking-news", "user_102", "facebook"));
        dashboard.processEvent(new AnalyticsEvent("/sports/championship", "user_201", "google"));
        dashboard.processEvent(new AnalyticsEvent("/sports/championship", "user_202", "direct"));
        dashboard.processEvent(new AnalyticsEvent("/tech/ai-update", "user_301", "twitter"));
        dashboard.processEvent(new AnalyticsEvent("/article/breaking-news", "user_103", "google"));

        dashboard.displayDashboard();
    }
}

 main
