package ymsli.com.adminemployee.ui.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.CalendarModel;

public class GridCalAdapter extends ArrayAdapter<CalendarModel> {
    public GridCalAdapter(@NonNull Context context, ArrayList<CalendarModel> calendarList) {
        super(context, 0, calendarList);
    }
    //setting layout according to data fetched
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.cal_days, parent, false);
        }
        CalendarModel calendarModel=getItem(position);
        TextView tv=listitemView.findViewById(R.id.tvCal);
        if((position+1)%7==0||(position+2)%7==0)tv.setTextColor(Color.parseColor("#FF0000"));
        tv.setText(calendarModel.getDate());
        if(calendarModel.isSelected()){
            tv.setTextColor(Color.parseColor("#FF0000"));
        }else{
            tv.setTextColor(Color.parseColor("#141414"));
        }
        return listitemView;
    }
}
