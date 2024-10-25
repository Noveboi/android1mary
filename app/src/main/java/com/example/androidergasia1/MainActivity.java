package com.example.androidergasia1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SharedPreferences with painting data
        initializeSharedPreferences();

        // Setup button to navigate to GalleryActivity
        Button viewGalleryButton = findViewById(R.id.viewGalleryButton);
        viewGalleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GalleryActivity.class);
            startActivity(intent);
        });

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            finishAffinity();
            System.exit(0);
        });
    }

    // Method to initialize SharedPreferences
    private void initializeSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("GalleryAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        List<Painting> paintings = new ArrayList<>();

        paintings.add(new Painting("Woman with a Parasol", "Claude Monet", "Claude Monet's 'Woman with a Parasol' (1875) captures a fleeting moment on a breezy summer day, depicting his wife and son in a lush, sunlit landscape with delicate brushstrokes that convey the movement of air and light.", R.drawable.monet1));
        paintings.add(new Painting("Weeping Willow", "Claude Monet", "Claude Monet's *Weeping Willow* (1918-1919) portrays the melancholy beauty of a willow tree with expressive, layered brushstrokes, reflecting the artist's grief and the turbulence of World War I while capturing the interplay of light and shadow.", R.drawable.monet2));
        paintings.add(new Painting("Water Lilies and the Japanese Bridge", "Claude Monet", "Claude Monet's *Water Lilies and the Japanese Bridge* (1899) depicts a tranquil garden scene with a curved bridge over a pond, using vibrant colors and fluid brushstrokes to capture the shimmering reflections and lush foliage of his beloved water garden at Giverny.", R.drawable.monet3));


        String paintingsJson = convertPaintingsToJson(paintings);

        // Store JSON string in SharedPreferences
        editor.putString("paintings", paintingsJson);
        editor.apply(); // Save changes
    }

    private String convertPaintingsToJson(List<Painting> paintings) {
        JSONArray jsonArray = new JSONArray();
        for (Painting painting : paintings) {
            jsonArray.put(painting.toJSON());
        }

        // Create a JSONObject to wrap the array
        JSONObject paintingsJsonObject = new JSONObject();
        try {
            paintingsJsonObject.put("paintings", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return paintingsJsonObject.toString(); // Return the JSON string
    }
}
