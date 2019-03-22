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
import com.sakecfest.shahandanchor.ashish.pratishtha.EventDetail;
import com.sakecfest.shahandanchor.ashish.pratishtha.Interface.ItemClickListener;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.R;
import java.util.List;


public class Sponsor_Adapter extends RecyclerView.Adapter<Sponsor_Adapter.MyViewHolder> {

  private List<EventModel> listItems;
  private Context context;
  public String id;
  String TAG = "Check Onclick";

  public class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView title, event_id;
    public ImageView imgview;
    public CardView cv;

    public MyViewHolder(View view) {
      super(view);
      title = view.findViewById(R.id.sponsortitle);
      imgview = view.findViewById(R.id.sponsorimage);
      event_id = view.findViewById(R.id.eventid);
      cv = view.findViewById(R.id.cv);
    }


  }


  public Sponsor_Adapter(List<EventModel> title, Context context) {
    this.listItems = title;
    this.context = context;
  }


  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.sponsor_template, parent, false);

    return new MyViewHolder(itemView);
  }


  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    final EventModel list = listItems.get(position);
    YoYo.with(Techniques.Landing).duration(700).playOn(holder.cv);
    holder.title.setText(list.getTitle());
    holder.event_id.setText(list.getId());
    RequestBuilder<Drawable> thumbnailRequest = Glide
        .with(context)
        .load(list.getImage());
    Glide.with(context).load(list.getImage()).thumbnail(thumbnailRequest).into(holder.imgview);
  }


  @Override
  public int getItemCount() {
    return listItems.size();
  }

}
