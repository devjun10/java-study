package 옵저버패턴_V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User implements Observer {

    private Long id;
    private String name;
    private List<String> alarms = new ArrayList<>();
    private List<Subjects> youtubers = new ArrayList<>();

    public User(String name) {
        this.id = Youtube.createUserId();
        this.name = name;
        Youtube.save(this);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void update(Long youtuberId, Long userId, String content) {
        alarms.add(content);
        for (Subjects subject : youtubers) {
            if (Objects.equals(subject.getId(), youtuberId)) {
                subject.notifyInformation();
            }
        }
    }

    @Override
    public void subscribe(Subjects subject) {
        subject.register(this);
        this.youtubers.add(subject);
    }

    @Override
    public String toString() {
        return "사용자[" +
                "아이디=" + id +
                ", 이름='" + name + '\'' +
                ", 알림 메시지=" + alarms +
                ", 유튜버 목록=" + youtubers +
                ']';
    }
}
