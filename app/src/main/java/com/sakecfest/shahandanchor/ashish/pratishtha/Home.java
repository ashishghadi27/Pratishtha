package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.EventAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import java.util.Objects;

public class Home extends AppCompatActivity {

  private String url;
  private FirebaseFirestore db;



  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          FragmentManager fragmentManager = getSupportFragmentManager();
          Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
          if (currentFragment instanceof Event) {
            Log.v("Same", "Fragment");
          } else {
            getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, new Event(), "event_fragment")
                .commit();
          }
          return true;

        case R.id.sakecmun:
          getlink();
          Intent i = new Intent(Intent.ACTION_VIEW);
          i.setData(Uri.parse(url));
          startActivity(i);
          return true;

        case R.id.navigation_schedule:
          fragmentManager = getSupportFragmentManager();
          currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
          if (currentFragment instanceof Schedule) {
            Log.v("Same", "Fragment");
          } else {
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new Schedule(), "schedule_fragment").commit();
          }
          return true;

        case R.id.navigation_info:
          fragmentManager = getSupportFragmentManager();
          currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
          if (currentFragment instanceof Info) {
            Log.v("Same", "Fragment");
          } else {
            getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, new Info(), "info_fragment")
                .commit();
          }
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    getSupportFragmentManager()
        .beginTransaction().replace(R.id.fragment_container, new Event(), "event_fragment")
        .commit();
    db = FirebaseFirestore.getInstance();
    getlink();
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

  private void getlink(){
    db.collection("mun")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                url = document.getData().get("link")+"";
              }

            }
          }
        });
  }


}
