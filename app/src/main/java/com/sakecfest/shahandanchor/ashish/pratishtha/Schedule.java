package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.EventAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.ScheduleAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.ScheduleModel;
import dmax.dialog.SpotsDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Schedule extends Fragment {

  String TAG="CHECK";
  private FirebaseFirestore db;
  private List<ScheduleModel> listItems;
  private ScheduleAdapter adapter;
  private RecyclerView recyclerView;
  private android.app.AlertDialog dialog;


  public Schedule() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_schedule, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    db = FirebaseFirestore.getInstance();
    recyclerView = view.findViewById(R.id.scherecycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    listItems = new ArrayList<>();
    dialog = new SpotsDialog.Builder().setContext(getContext()).build();
    dialog.setCancelable(false);
    loadSchedule();

  }

  private void loadSchedule(){

    dialog.show();
    listItems.clear();
    db.collection("schedule")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                ScheduleModel item = new ScheduleModel(document.getData().get("event")+"", document.getData().get("time")+"", document.getData().get("link")+"");
                listItems.add(item);
                adapter = new ScheduleAdapter(listItems, getContext());
                recyclerView.setAdapter(adapter);
                Log.d(TAG, document.getId() + " => " + document.getData().get("event"));
              }
              dialog.dismiss();
            } else {
              Log.d(TAG, "Error getting documents: ", task.getException());
            }
          }
        });

  }

}
