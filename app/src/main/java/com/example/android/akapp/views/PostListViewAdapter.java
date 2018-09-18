package com.example.android.akapp.views;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android.akapp.R;
import com.example.android.akapp.models.entities.PostEntity;

import java.util.List;

public class PostListViewAdapter extends BaseAdapter {

    private Context context;
    private LiveData<List<PostEntity>> postList;
    private LayoutInflater layoutInflater;

    public PostListViewAdapter(Context context, LiveData<List<PostEntity>> postList){

        this.context = context;
        this.postList = postList;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {

        return postList.getValue().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convetView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.post_row, parent, false);
        TextView title = (TextView) view.findViewById(R.id.textViewTitle);
        TextView context = (TextView) view.findViewById(R.id.textViewContext);
        TextView date = (TextView) view.findViewById(R.id.textViewDate);

        date.setText(postList.getValue().get(position).getCreationDate());
        title.setText(postList.getValue().get(position).getTitle());
        context.setText(postList.getValue().get(position).getContext());


        return view;
    }
}
