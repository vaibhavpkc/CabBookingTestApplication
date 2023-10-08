package com.vaibhav.cabbooking.model;

public enum RATING {
    ONE_STAR(1),
    TWO_STARS(2),
    THREE_STARS(3),
    FOUR_STARS(4),
    FIVE_STARS(5);

    private int value;

    RATING(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
