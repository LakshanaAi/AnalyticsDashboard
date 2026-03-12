import java.util.*;

/**
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

        PriorityQueue<Map.Entry<String, Integer>> pq =
                new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(pageViews.entrySet());

        System.out.println("\nTop Pages:");

        int rank = 1;

        while (!pq.isEmpty() && rank <= 10) {

            Map.Entry<String, Integer> entry = pq.poll();
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
    }

    public static void main(String[] args) {

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

