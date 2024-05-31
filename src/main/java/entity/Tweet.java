package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Tweet {
    private Integer id;
    private String content;
    private LocalDate date;
    private LocalTime time;
    private User user;//private  User user

    public Tweet(String content, User user) {
        this.content = content;
        this.user = user;
        this.date=LocalDate.now();
        this.time=LocalTime.now();
    }
    public Tweet(String content) {
        this.content = content;
    }

    public Tweet(){}

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    @Override
//    public String toString() {
//        return "Tweet{" +
//                "id=" + id +
//                ", content='" + content + '\'' +
//                ", date=" + date +
//                ", time=" + time +
//                '}';
//    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
