package com.sakecfest.shahandanchor.ashish.pratishtha;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Info extends Fragment {

  private CardView cv, cv2;

  public Info() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_info, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    cv = view.findViewById(R.id.cv_trustee);
    cv2 = view.findViewById(R.id.cv_sponsor);
    cv.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),GalleryPreview.class);
        intent.putExtra("path", "trustee");
        startActivity(intent);
      }
    });

    cv2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(v.getContext(),Sponsor.class);
        startActivity(intent);
      }
    });
  }
}
