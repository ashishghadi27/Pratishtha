package com.sakecfest.shahandanchor.ashish.pratishtha.Adapter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.github.vipulasri.timelineview.TimelineView;
import com.sakecfest.shahandanchor.ashish.pratishtha.EventDetail;
import com.sakecfest.shahandanchor.ashish.pratishtha.Interface.ItemClickListener;
import com.sakecfest.shahandanchor.ashish.pratishtha.Model.ScheduleModel;
import com.sakecfest.shahandanchor.ashish.pratishtha.R;
import java.util.List;


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

  private List<ScheduleModel> listItems;
  private Context context;
  public String id;
  private TimelineView mTimelineView;
  String TAG = "Check Onclick";
  private ItemClickListener itemClickListener;

  public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView title, time, desc;

    public MyViewHolder(View view, int viewType) {
      super(view);
      title = view.findViewById(R.id.event_name);
      time = view.findViewById(R.id.time);
      desc = view.findViewById(R.id.desc);
      mTimelineView = (TimelineView) itemView.findViewById(R.id.timeline);
      mTimelineView.initLine(viewType);
      view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

      final Dialog dialog = new Dialog(context);
      dialog.setContentView(R.layout.alert_dialog_layout);
      TextView text = (TextView) dialog.findViewById(R.id.title);
      TextView date = (TextView) dialog.findViewById(R.id.date);
      TextView descr = (TextView) dialog.findViewById(R.id.description);
      date.setText(time.getText());
      String descrp = desc.getText().toString().replace("\\n","\n");
      if(descrp.equals("")||descrp.isEmpty()){
        descr.setVisibility(View.GONE);
      }else descr.setText(descrp);
      text.setText(title.getText());
      dialog.show();

    }
  }


  public ScheduleAdapter(List<ScheduleModel> title, Context context) {
    this.listItems = title;
    this.context = context;
  }


  @Override
  public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.schedule_template, parent, false);

    return new MyViewHolder(itemView,viewType);
  }


  @Override
  public void onBindViewHolder(final MyViewHolder holder, int position) {
    final ScheduleModel list = listItems.get(position);
    holder.title.setText(list.getName());
    holder.time.setText(list.getTime());
    holder.desc.setText(list.getLink());
  }


  @Override
  public int getItemCount() {
    return listItems.size();
  }

}
