package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
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
import com.sakecfest.shahandanchor.ashish.pratishtha.Adapter.EventAdapter;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.DayModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import dmax.dialog.SpotsDialog;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class days extends Fragment {

  private String id="";
  private RecyclerView recyclerView;
  private Day_Adapter adapter;
  private FirebaseFirestore db;
  private TextView nodata;
  private List<DayModel> listItems;
  private android.app.AlertDialog dialog;

  public days() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


   }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    db = FirebaseFirestore.getInstance();
    recyclerView = view.findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    listItems = new ArrayList<>();
    nodata = view.findViewById(R.id.nodatatxt);
    dialog = new SpotsDialog.Builder().setContext(getContext()).build();
    dialog.setCancelable(false);
    getDays();
    //loadUpcomingEvent();

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    assert getArguments() != null;
    id = getArguments().getString("id");
    Log.v("IDIS", id);

    return inflater.inflate(R.layout.fragment_days, container, false);

  }

  private void getDays(){
    dialog.show();
    Log.v("DBEXIST", db.collection("events").document(id).collection("days").get()+"");
    db.collection("events").document(id).collection("days")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
          @Override
          public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if (task.isSuccessful() && !task.getResult().isEmpty()) {
              for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {

                  Log.d("DATA", document.getId() + " => " + document.getData());
                  DayModel item = new DayModel(document.getData().get("day")+"", document.getId(), id, document.getData().get("image")+"");
                  listItems.add(item);
                  adapter = new Day_Adapter(listItems, getContext());
                  recyclerView.setAdapter(adapter);

              }
              dialog.dismiss();
            } else {
              Log.d("DATA", "Error getting documents: ", task.getException());
              recyclerView.setVisibility(View.GONE);
              nodata.setVisibility(View.VISIBLE);
              dialog.dismiss();
            }
          }
        });

  }
}
