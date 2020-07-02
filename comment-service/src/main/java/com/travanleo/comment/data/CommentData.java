package com.travanleo.comment.data;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CommentData {

    private String title;

    private String description;

    private String createdAt;

    private String updatedAt;

    public CommentData(final String title, final String description, final String createdAt, final String updatedAt) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
