package 옵저버패턴_V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameYoutuber implements Subjects{

    private Long id;
    private String name;
    private List<Long> subscribers = new ArrayList<>();
    private Youtube youtube;
    private String content;

    public GameYoutuber(String name, Youtube youtube) {
        this.name = name;
        this.id = Youtube.createYoutuberId();
        this.youtube = youtube;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
        update(content);
    }

    @Override
    public void register(Observer observer) {
        subscribers.add(observer.getId());
    }

    @Override
    public void update(String content) {
        for (Long userId : subscribers) {
            youtube.update(this.id, userId, content);
        }
    }

    @Override
    public void notifyInformation() {
        System.out.println(this.name + "님이 이번 주 [" + content + "] 를 업로드 하셨습니다.");
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameYoutuber that = (GameYoutuber) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "게임 유튜버[" +
                "아이디=" + id +
                ", 구독자=" + subscribers +
                ", 콘텐츠='" + content + '\'' +
                '}';
    }
}
