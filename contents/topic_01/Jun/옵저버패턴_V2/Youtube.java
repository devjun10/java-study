package 옵저버패턴_V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Youtube {

    private static List<Observer> subscribers = new ArrayList<>();
    private static Long userI = 0L;
    private static Long youtuberId = 0L;

    public static void save(User user) {
        subscribers.add(user);
    }

    public void update(Long youtuberId, Long userId, String content) {
        for (Observer observer : subscribers) {
            if (Objects.equals(observer.getId(), userId)) {
                observer.update(youtuberId, userId, content);
            }
        }
    }

    public static Long createUserId() {
        userI += 1;
        return userI;
    }

    public static Long createYoutuberId() {
        youtuberId += 1;
        return youtuberId;
    }
}
