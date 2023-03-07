package ymsli.com.adminemployee.ui.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;

//adapter class for showing list of Employees
public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    private Context context;
    private List<User> userList;
    private EmployeeAdapter.ItemClickListener mItemClickListener;
    private EmployeeAdapter.ItemClickListener2 itemClickListener2;

    public EmployeeAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public EmployeeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //here we define the layout of an item of the recycler view
        View listItem= layoutInflater.inflate(R.layout.item_employee, parent, false);
        EmployeeAdapter.ViewHolder viewHolder = new EmployeeAdapter.ViewHolder(listItem);
        return viewHolder;
    }
    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }
    public void addItemClickListener2(ItemClickListener2 listener2){itemClickListener2=listener2;}

    //setting layout according to data fetched
    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        User u=userList.get(position);
        holder.textView.setText("Name: "+u.getUserName());
        holder.ein.setText("Employee Id: "+u.getEmpNumber());
        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(position);
                }
            }
        });
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener2 != null) {
                    itemClickListener2.onItemClick2(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ConstraintLayout cl;
        public TextView ein;
        public LinearLayout ll;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView=itemView.findViewById(R.id.tvName);
            this.ein=itemView.findViewById(R.id.tvEidItem);
            this.cl=itemView.findViewById(R.id.cl1);
            this.ll=itemView.findViewById(R.id.ll4);
        }
    }
    //Define your Interface method here
    //click interfaces are defined here
    public interface ItemClickListener {
        void onItemClick(int position);
    }
    public interface ItemClickListener2{
        void onItemClick2(int position);
    }
}
