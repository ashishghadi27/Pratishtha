package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Gallery extends AppCompatActivity {

  private String eventid, dayid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gallery);
    Intent intent = getIntent();
    eventid = intent.getStringExtra("eventid");
    dayid = intent.getStringExtra("dayid");
    Bundle bundle = new Bundle();
    bundle.putString("eventid", eventid);
    bundle.putString("dayid", dayid);
    gallery fragobj = new gallery();
    fragobj.setArguments(bundle);

    getSupportFragmentManager()
        .beginTransaction().replace(R.id.galleryfragment_container,fragobj,"gallery_fragment").commit();

  }
}
