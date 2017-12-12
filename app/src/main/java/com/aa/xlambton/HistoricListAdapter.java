package com.aa.xlambton;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aa.xlambton.Model.Agent;
import com.aa.xlambton.Model.Mission;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by araceliteixeira on 11/12/17.
 */

public class HistoricListAdapter extends ArrayAdapter {
    public HistoricListAdapter(Context context, int layoutResource, List missions) {
        super(context, layoutResource, missions);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = ViewGroup.inflate(parent.getContext(), R.layout.historic_list_cell, null);
        }

        Mission mission = (Mission) getItem(position);

        if (mission != null) {
            TextView missionName = (TextView) view.findViewById(R.id.historic_cell_mission);
            TextView date = (TextView) view.findViewById(R.id.historic_cell_date);
            TextView status = (TextView) view.findViewById(R.id.historic_cell_date);

            if (missionName != null) {
                missionName.setText(mission.getName());
            }
            if (date != null) {
                date.setText(new SimpleDateFormat("dd/MM/yyyy").format(mission.getDate()));
            }
            if (status != null) {
                status.setText(mission.getStatus());
            }
        }

        return view;
    }
}
