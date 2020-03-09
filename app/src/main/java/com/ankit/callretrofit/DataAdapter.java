package com.ankit.callretrofit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DataAdapter extends BaseAdapter {
    private Context context;
    private List<Example> data;
    DataAdapter(Context context, List<Example> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh;
        if(view == null) {
            view=LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
            vh = new ViewHolder();
            vh.tvUserId = view.findViewById(R.id.tvUserId);
            vh.tvTitle =  view.findViewById(R.id.tvTitle);
            vh.tvId = view.findViewById(R.id.tvId);
            vh.tvCompleted =  view.findViewById(R.id.tvCompleted);
            view.setTag(vh);
        }
        else{
            vh = (ViewHolder) view.getTag();
        }
       final Example m = data.get(position);
        vh.tvTitle.setText(m.getTitle());
        vh.tvUserId.setText(m.getUserId().toString());
        vh.tvId.setText(m.getId().toString());
        vh.tvCompleted.setText(m.getCompleted().toString());
        if (m.getCompleted()){
            vh.tvUserId.setTextColor(Color.RED);
            vh.tvTitle.setTextColor(Color.RED);
            vh.tvId.setTextColor(Color.RED);
            vh.tvCompleted.setTextColor(Color.RED);

        }
        else {
            vh.tvUserId.setTextColor(Color.BLUE);
            vh.tvTitle.setTextColor(Color.BLUE);
            vh.tvId.setTextColor(Color.BLUE);
            vh.tvCompleted.setTextColor(Color.BLUE);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, m.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message= "userId: "+ m.getUserId().toString() +"\nId: "+ m.getId().toString()+ "\nTitle: " + m.getTitle()
                + "\nCompleted: " + m.getCompleted().toString();
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle( "Hello" )
                        .setMessage(message)
//     .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//      public void onClick(DialogInterface dialoginterface, int i) {
//          dialoginterface.cancel();
//          }})
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                dialoginterface.dismiss();
                            }
                        }).show();
            }
        });
        return view;
    }
    private static class ViewHolder {
        private TextView tvUserId;
        private TextView tvId;
        private TextView tvTitle;
        private TextView tvCompleted;
    }
}
