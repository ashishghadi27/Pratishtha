package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.content.Intent;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;

public class GalleryPreview extends AppCompatActivity {

  ImageView GalleryPreviewImg;
  String path;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gallery_preview);
    Intent intent = getIntent();
    path = intent.getStringExtra("path");
    if(path.equals("trustee")){
      GalleryPreviewImg = (ImageView) findViewById(R.id.GalleryPreviewImg);
      Glide.with(GalleryPreview.this)
          .load(R.drawable.trustboard) // Uri of the picture
          .into(GalleryPreviewImg);
    }
    else {
      GalleryPreviewImg = (ImageView) findViewById(R.id.GalleryPreviewImg);
      Glide.with(GalleryPreview.this)
          .load(path) // Uri of the picture
          .into(GalleryPreviewImg);
    }
  }
}
