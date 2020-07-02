package com.travanleo.comment.domain;

import com.travanleo.comment.api.JsonCommand;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedHashMap;
import java.util.Map;

@Document(collection = "comment")
public class Comment {

    @Id
    private String id;
    private String title;
    private String description;
    private DateTime createdAt;
    private DateTime updatedAt;
    private String createdBy;

    public Comment() {}

    public Comment(final String title, final String description, final String createdBy) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.updatedAt = DateTime.now();
    }

    public Comment(final String title, final String description, final String createdBy, final DateTime createdAt) {
        this.title = title;
        this.description = description;
        this.createdAt = DateTime.now();
        this.createdBy = createdBy;
        this.updatedAt = DateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void updateTitle(final String title) {
        this.title = title;
    }

    public void updateDescription(final String description) {
        this.description = description;
    }

    public Map<String, Object> update(final JsonCommand command) {
        final Map<String, Object> actualChanges = new LinkedHashMap<>(9);
        if (command.isChangeInStringParameterNamed("title", this.title)) {
            final String newValue = command.stringValueOfParameterNamed("title");
            actualChanges.put("title", newValue);
        }
        if (command.isChangeInStringParameterNamed("description", this.description)) {
            final String newValue = command.stringValueOfParameterNamed("description");
            actualChanges.put("description", newValue);
        }
        return actualChanges;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
