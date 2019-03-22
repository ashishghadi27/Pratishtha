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
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.sakecfest.shahandanchor.ashish.pratishtha.EventDetail;
import com.sakecfest.shahandanchor.ashish.pratishtha.GalleryPreview;
import com.sakecfest.shahandanchor.ashish.pratishtha.Interface.ItemClickListener;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.EventModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.ImageModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.R;
import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {

  private List<ImageModel> listItems;
  private Context context;
  public String id;
  String TAG = "Check Onclick";
  private ItemClickListener itemClickListener;

  public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView imgview;
    public TextView imglink;
    public CardView cv;

    public MyViewHolder(View view) {
      super(view);
      imgview = view.findViewById(R.id.galleryimage);
      imglink = view.findViewById(R.id.imagelink);
      cv = view.findViewById(R.id.cv);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      Intent intent = new Intent(v.getContext(),GalleryPreview.class);
      intent.putExtra("path", imglink.getText());
      v.getContext().startActivity(intent);

    }
  }


  public ImageAdapter(List<ImageModel> title, Context context) {
    this.listItems = title;
    this.context = context;
  }


  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.gallery_template, parent, false);

    return new MyViewHolder(itemView);
  }


  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    final ImageModel list = listItems.get(position);
    YoYo.with(Techniques.Landing).duration(700).playOn(holder.cv);
    holder.imglink.setText(list.getImagelink());
    RequestBuilder<Drawable> thumbnailRequest = Glide
        .with(context)
        .load(list.getImagelink());

    Glide.with(context).load(list.getImagelink()).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)).thumbnail(thumbnailRequest).into(holder.imgview);
  }


  @Override
  public int getItemCount() {
    return listItems.size();
  }

}
