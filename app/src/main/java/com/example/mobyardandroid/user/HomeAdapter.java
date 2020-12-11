package com.example.mobyardandroid.user;

public class HomeAdapter {
    int image, cardNum;
    String title, id, description, cardNumStr;

    public HomeAdapter(int image, String title, String id, String description, int cardNum) {
        this.image = image;
        this.title = title;
        this.id = id;
        this.description = description;
        this.cardNum = cardNum;
        this.cardNumStr = String.valueOf(cardNum);
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCardNum() {
        return cardNum;
    }

    public String getCardNumStr() {
        return cardNumStr;
    }
}
