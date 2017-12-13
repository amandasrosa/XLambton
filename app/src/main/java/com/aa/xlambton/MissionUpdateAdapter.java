package com.aa.xlambton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by araceliteixeira on 12/12/17.
 */

public class MissionUpdateAdapter extends ArrayAdapter {
    public MissionUpdateAdapter(Context context, int layoutResource, List paths) {
        super(context, layoutResource, paths);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.mission_update_cell, null);
        }

        String path = (String) getItem(position);
        if (path != null) {
            ImageView image = (ImageView) view.findViewById(R.id.mission_update_cell_image);
            TextView label = (TextView) view.findViewById(R.id.mission_update_cell_label);

            if (image != null) {
                image.setImageBitmap(BitmapHelper.getScaledBitmap(getContext(), path));
            }
            if (label != null) {
                File f = new File(path);
                label.setText(f.getName());
            }
        }

        return view;
    }
}
