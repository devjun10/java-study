package observer2;

//
public interface Observer {
    Long getId();
    void update(Long youtuberId, Long userId, String content);
    void subscribe(Subjects subject);
}
