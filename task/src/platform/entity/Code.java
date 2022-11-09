package platform.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Code {

    @Id
    @JsonIgnore
    private String id;
    private String code;
    private LocalDateTime date;
    private long time;
    private int views;

    @JsonIgnore
    private int viewCount;

    public Code() {
        this.date = LocalDateTime.now();
        this.id = UUID.randomUUID().toString();
    }

    @JsonIgnore
    public boolean isAccessible() {
        long elapsedSeconds = Duration
                .between(date, LocalDateTime.now())
                .getSeconds();
        return (views == 0 || views > viewCount) && (time == 0 || time > elapsedSeconds);
    }

    public void increaseViewCount() {
        viewCount += 1;
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
    }

    @JsonGetter("time")
    public long getRemainingTime() {
        if (time == 0)
            return 0;
        long elapsedSeconds = Duration
                .between(date, LocalDateTime.now())
                .toSeconds();
        return time > elapsedSeconds ? time - elapsedSeconds : 0;
    }

    @JsonGetter("views")
    public long getRemainingViews() {
        if (views == 0)
            return 0;
        return views > viewCount ? views - viewCount : 0;
    }

    public long getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }
}
