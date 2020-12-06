package com.emon.emonsassessment;

import android.content.Intent;

import com.emon.emonsassessment.Show;

public class Post {

    private String score;
    private Show show;

    public Post(String score, Show show) {
        this.score = score;
        this.show = show;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }
}
