package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EventDetail extends AppCompatActivity {

  private String id;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_detail);
    Intent intent = getIntent();
    id = intent.getStringExtra("id");
    Bundle bundle = new Bundle();
    bundle.putString("id", id);
    days fragobj = new days();
    fragobj.setArguments(bundle);
    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container2,fragobj,"days_fragment").commit();
  }
}
