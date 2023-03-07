package ymsli.com.adminemployee.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.Team;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private Context context;
    private List<Team> teamList;
    private ItemClickListener mItemClickListener;
    private ItemClickListener1 mItemClickListener1;
    private ItemClickListener2 mItemClickListener2;


    //constructor of Adapter
    public TeamAdapter(Context context, List<Team> teamList) {
        this.context = context;
        this.teamList = teamList;
    }
    @NonNull
    @Override
    public TeamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_list_team, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    //defining click listener interfaces
    public void addItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }
    public void addItemClickListener1(ItemClickListener1 listener1){mItemClickListener1=listener1;}
    public void addItemClickListener2(ItemClickListener2 listener2){mItemClickListener2=listener2;}

    //setting layout according to data fetched
    @Override
    public void onBindViewHolder(@NonNull TeamAdapter.ViewHolder holder, int position) {
        Team t= teamList.get(position);
        holder.textView.setText("Team Name: "+t.getName());
        holder.textView2.setText("Tech Stack being used: "+t.getTech());

        holder.cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mItemClickListener.onItemClick(holder.getAdapterPosition());
            }
        });
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener1.onItemClick1(holder.getAdapterPosition());
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener2.onItemClick2(holder.getAdapterPosition());
            }
        });
    }

    //to get number of items in the recycler view
    @Override
    public int getItemCount() {
        return teamList.size();
    }

    //defining ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public TextView textView2;
        public ConstraintLayout cl;
        public LinearLayout ll;
        public ImageView iv;
        public ImageView btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView=itemView.findViewById(R.id.tvTeam);
            this.textView2=itemView.findViewById(R.id.tech);
            this.cl=itemView.findViewById(R.id.cl1);
            this.ll=itemView.findViewById(R.id.ll2);
            this.iv=itemView.findViewById(R.id.ivDel);
            this.btn=itemView.findViewById(R.id.btnAddMember);
        }
    }
    //Define your Interface method here
    public interface ItemClickListener {
        void onItemClick(int position);
    }
    public interface ItemClickListener1 {
        void onItemClick1(int position);
    }
    public interface ItemClickListener2 {
        void onItemClick2(int position);
    }

}
