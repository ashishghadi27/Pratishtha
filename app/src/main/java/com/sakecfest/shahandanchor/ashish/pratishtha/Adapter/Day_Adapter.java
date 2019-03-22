package com.sakecfest.shahandanchor.ashish.pratishtha.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sakecfest.shahandanchor.ashish.pratishtha.Gallery;
import com.sakecfest.shahandanchor.ashish.pratishtha.Interface.ItemClickListener;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.DayModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.R;
import java.util.List;


public class Day_Adapter extends RecyclerView.Adapter<Day_Adapter.MyViewHolder> {

  private List<DayModel> listItems;
  private Context context;
  public String id;
  String TAG = "Check Onclick";
  private ItemClickListener itemClickListener;

  public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView title, day_id, event_id;
    public ImageView dayimgview;
    public CardView cv;

    public MyViewHolder(View view) {
      super(view);
      title = view.findViewById(R.id.daytitle);
      day_id = view.findViewById(R.id.dayid);
      event_id = view.findViewById(R.id.event_id);
      cv = view.findViewById(R.id.cv);
      dayimgview = view.findViewById(R.id.dayimage);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      Intent intent = new Intent(v.getContext(),Gallery.class);
      intent.putExtra("dayid", day_id.getText());
      intent.putExtra("eventid", event_id.getText());
      v.getContext().startActivity(intent);

    }
  }


  public Day_Adapter(List<DayModel> title, Context context) {
    this.listItems = title;
    this.context = context;
  }


  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.day_template, parent, false);

    return new MyViewHolder(itemView);
  }


  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    final DayModel list = listItems.get(position);
    YoYo.with(Techniques.ZoomIn).duration(700).playOn(holder.cv);
    holder.title.setText(list.getDays());
    holder.day_id.setText(list.getId());
    holder.event_id.setText(list.getEvent_id());
    RequestBuilder<Drawable> thumbnailRequest = Glide
        .with(context)
        .load(list.getImage());
    Glide.with(context).load(list.getImage()).thumbnail(thumbnailRequest).into(holder.dayimgview);
  }


  @Override
  public int getItemCount() {
    return listItems.size();
  }

}
