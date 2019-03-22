package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.Day_Adapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.ImageAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.DayModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.ImageModel;
import dmax.dialog.SpotsDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class gallery extends Fragment {

  private String eventid="", dayid="";
  private RecyclerView recyclerView;
  private ImageAdapter adapter;
  private FirebaseFirestore db;
  private TextView nodata;
  private List<ImageModel> listItems;
  private android.app.AlertDialog dialog;

  public gallery() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    eventid = getArguments().getString("eventid");
    dayid = getArguments().getString("dayid");
    return inflater.inflate(R.layout.fragment_gallery, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    db = FirebaseFirestore.getInstance();
    recyclerView = view.findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
    listItems = new ArrayList<>();
    dialog = new SpotsDialog.Builder().setContext(getContext()).build();
    dialog.setCancelable(false);
    getGallery();
  }

  private void getGallery(){
    dialog.show();
    Log.v("DBEXIST", db.collection("events").document(eventid).collection("days").document(dayid).collection("gallery")+"");
    db.collection("events").document(eventid).collection("days").document(dayid).collection("gallery")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if (task.isSuccessful() && !task.getResult().isEmpty()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {

                Log.d("DATA", document.getId() + " => " + document.getData());
                ImageModel item = new ImageModel(document.getData().get("image")+"");
                listItems.add(item);
                adapter = new ImageAdapter(listItems, getContext());
                recyclerView.setAdapter(adapter);

              }
              dialog.dismiss();
            } else {
              Log.d("DATA", "Error getting documents: ", task.getException());
              /*recyclerView.setVisibility(View.GONE);
              nodata.setVisibility(View.VISIBLE);*/
              dialog.dismiss();
            }
          }
        });

  }
}
