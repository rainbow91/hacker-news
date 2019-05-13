package org.superbiz.moviefun.stories;

import javax.persistence.*;

@Entity
@Table(name = "story")
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int rating;
    private String title;
    private String url;

    public Story(int id, String title, String url, int rating) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.rating = rating;
    }

    public Story(String title, String url, int rating) {
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
