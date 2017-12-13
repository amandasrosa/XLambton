package com.aa.xlambton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aa.xlambton.Model.Agent;

import java.util.List;

/**
 * Created by araceliteixeira on 11/12/17.
 */

public class AgentListAdapter extends ArrayAdapter {
    private int layoutResource;

    public AgentListAdapter(Context context, int layoutResource, List agents) {
        super(context, layoutResource, agents);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.agent_list_cell, null);
        }
        /*if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }*/

        Agent agent = (Agent) getItem(position);

        if (agent != null) {
            ImageView photo = (ImageView) view.findViewById(R.id.list_cell_photo);
            TextView name = (TextView) view.findViewById(R.id.list_cell_name);
            TextView level = (TextView) view.findViewById(R.id.list_cell_level);

            if (photo != null) {
                photo.setImageBitmap(BitmapHelper.getScaledBitmap(getContext(), agent.getPhotoPath()));
            }
            if (name != null) {
                name.setText(agent.getName());
            }
            if (level != null) {
                level.setText(agent.getLevel());
            }
        }

        return view;
    }
}
