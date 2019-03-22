package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.EventAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import dmax.dialog.SpotsDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Event extends Fragment {

  String TAG="CHECK";
  private FirebaseFirestore db;
  private List<EventModel> listItems;
  private List<EventModel> listItems2;
  private EventAdapter adapter;
  private RecyclerView recyclerView;
  private android.app.AlertDialog dialog;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {


    return inflater.inflate(R.layout.fragment_event, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    db = FirebaseFirestore.getInstance();
    recyclerView = view.findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
    listItems = new ArrayList<>();
    listItems2 =new ArrayList<>();
    dialog = new SpotsDialog.Builder().setContext(getContext()).build();
    dialog.setCancelable(false);
    loadEvent();
    //loadUpcomingEvent();

  }

  private void loadEvent(){

    dialog.show();
    listItems.clear();
    db.collection("events")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                EventModel item = new EventModel(document.getData().get("image")+"", document.getData().get("title")+"", document.getId());
                listItems.add(item);
                adapter = new EventAdapter(listItems, getContext());
                recyclerView.setAdapter(adapter);
                Log.d(TAG, document.getId() + " => " + document.getData().get("image"));
              }
              dialog.dismiss();
            } else {
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
          }
        });

  }

  /*private void loadUpcomingEvent(){
    listItems2.clear();
    db.collection("upcoming")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                EventModel item = new EventModel(document.getData().get("image")+"", document.getData().get("title")+"");
                listItems2.add(item);
                adapter = new EventAdapter(listItems2, getContext());
                recyclerView2.setAdapter(adapter);
                Log.d(TAG, document.getId() + " => " + document.getData().get("image"));
              }
            } else {
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
          }
        });

  }*/



}
