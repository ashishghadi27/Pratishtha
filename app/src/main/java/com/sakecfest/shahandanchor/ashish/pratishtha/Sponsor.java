package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.EventAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.Others_Adapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.Sponsor_Adapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import dmax.dialog.SpotsDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sponsor extends AppCompatActivity {

  private RecyclerView recyclerView, recyclerView2;
  String TAG="CHECK";
  private FirebaseFirestore db;
  private List<EventModel> listItems;
  private List<EventModel> listItems2;
  private android.app.AlertDialog dialog;
  private Others_Adapter adapter;
  private Sponsor_Adapter adapter2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    db = FirebaseFirestore.getInstance();
    setContentView(R.layout.activity_sponsor);
    recyclerView = findViewById(R.id.recyclerView);
    recyclerView2 = findViewById(R.id.recyclerView2);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    recyclerView2.setLayoutManager(new GridLayoutManager(this, 2));
    listItems = new ArrayList<>();
    listItems2 =new ArrayList<>();
    dialog = new SpotsDialog.Builder().setContext(this).build();
    dialog.setCancelable(false);
    load_partners();
    load_others();

  }

  private void load_partners(){
    dialog.show();
    listItems.clear();
    db.collection("sponsor")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                EventModel item = new EventModel(document.getData().get("image")+"", document.getData().get("type")+"", document.getId());
                listItems.add(item);
                adapter2 = new Sponsor_Adapter(listItems, Sponsor.this);
                recyclerView.setAdapter(adapter2);
                Log.d(TAG, document.getId() + " => " + document.getData().get("image"));
              }
              dialog.dismiss();
            } else {
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
          }
        });

  }

  private void load_others(){
    dialog.show();
    listItems2.clear();
    db.collection("others")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                EventModel item = new EventModel(document.getData().get("image")+"", " ", document.getId());
                listItems2.add(item);
                adapter = new Others_Adapter(listItems2, Sponsor.this);
                recyclerView2.setAdapter(adapter);
                Log.d(TAG, document.getId() + " => " + document.getData().get("image"));
              }
              dialog.dismiss();
            } else {
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
          }
        });
  }
}
