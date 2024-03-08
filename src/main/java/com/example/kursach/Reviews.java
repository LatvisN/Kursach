package com.example.kursach;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Reviews {

    private String reviews;

    public Reviews() {
    }

    public Reviews(String reviews) {
        this.reviews = reviews;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
