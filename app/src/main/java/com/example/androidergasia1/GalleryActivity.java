package com.example.androidergasia1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gallery);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("GalleryAppPrefs", Context.MODE_PRIVATE);
        String paintingsJson = sharedPreferences.getString("paintings", "");
        List<Painting> paintings = parsePaintings(paintingsJson);

        // Display data of the first painting (as an example)
        if (!paintings.isEmpty()) {
            Painting firstPainting = paintings.get(0);
            TextView paintingTitle = findViewById(R.id.paintingTitle);
            TextView paintingArtist = findViewById(R.id.paintingArtist);
            TextView paintingDescription = findViewById(R.id.paintingDescription);
            ImageView paintingImage = findViewById(R.id.paintingImage);

            paintingTitle.setText(firstPainting.getTitle());
            paintingArtist.setText(firstPainting.getArtist());
            paintingDescription.setText(firstPainting.getDescription());

            paintingImage.setImageResource(firstPainting.getImage());

            Painting secondPainting = paintings.get(1);
            TextView paintingTitle2 = findViewById(R.id.paintingTitle2);
            TextView paintingArtist2 = findViewById(R.id.paintingArtist2);
            TextView paintingDescription2 = findViewById(R.id.paintingDescription2);
            ImageView paintingImage2 = findViewById(R.id.paintingImage2);

            paintingTitle2.setText(secondPainting.getTitle());
            paintingArtist2.setText(secondPainting.getArtist());
            paintingDescription2.setText(secondPainting.getDescription());

            paintingImage2.setImageResource(secondPainting.getImage());

            Painting thirdPainting = paintings.get(2);
            TextView paintingTitle3 = findViewById(R.id.paintingTitle3);
            TextView paintingArtist3 = findViewById(R.id.paintingArtist3);
            TextView paintingDescription3 = findViewById(R.id.paintingDescription3);
            ImageView paintingImage3 = findViewById(R.id.paintingImage3);

            paintingTitle3.setText(thirdPainting.getTitle());
            paintingArtist3.setText(thirdPainting.getArtist());
            paintingDescription3.setText(thirdPainting.getDescription());

            paintingImage3.setImageResource(thirdPainting.getImage());
        }
    }

    private List<Painting> parsePaintings(String json) {
        List<Painting> paintingsList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray paintingsArray = jsonObject.getJSONArray("paintings");

            for (int i = 0; i < paintingsArray.length(); i++) {
                JSONObject paintingObject = paintingsArray.getJSONObject(i);
                String title = paintingObject.getString("title");
                String artist = paintingObject.getString("artist");
                String description = paintingObject.getString("description");
                int image = paintingObject.getInt("image");

                Painting painting = new Painting(title, artist, description, image);
                paintingsList.add(painting);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return paintingsList;
    }
}