package com.example.androidergasia1;

import org.json.JSONException;
import org.json.JSONObject;

public class Painting {
    private String title;
    private String artist;
    private String description;
    private int image;

    public Painting(String title, String artist, String description, int image) {
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", title);
            jsonObject.put("artist", artist);
            jsonObject.put("description", description);
            jsonObject.put("image", image);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
