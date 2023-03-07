package ymsli.com.adminemployee.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.ui.view.adapter.EmployeeAdapter;
import ymsli.com.adminemployee.ui.view.adapter.TeamAdapter;
import ymsli.com.adminemployee.ui.viewModel.EmployeeListViewModel;
import ymsli.com.adminemployee.ui.viewModel.HomeViewModel;

@AndroidEntryPoint
public class EmployeeListActivity extends AppCompatActivity implements EmployeeAdapter.ItemClickListener,EmployeeAdapter.ItemClickListener2 {
    EmployeeListViewModel mViewModel;
    List<User> userList;
    String teamName;
    Team teamData;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    RecyclerView rv;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_employee_list);
        Bundle bundle = getIntent().getExtras();
        teamName=bundle.getString("UserName");
        mViewModel = new ViewModelProvider(this).get(EmployeeListViewModel.class);

        tv1=findViewById(R.id.tvE1);
        tv2=findViewById(R.id.tvE2);
        tv3=findViewById(R.id.tvE3);
        tv4=findViewById(R.id.tvE4);
        rv=findViewById(R.id.rv);

        mViewModel.getTeamData(teamName);
        mViewModel.getEmployeeFromTeam(teamName);

        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //swipping action maker
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            private float swipeThreshold = 0.5f;
            int swipedPosition = -1;
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                viewHolder.itemView.findViewById(R.id.ll2).setVisibility(View.VISIBLE);
                return false;
            }
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX/4, dY, actionState, isCurrentlyActive);
            }

            //setting visibility setting for right and left swipe
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                if(direction==ItemTouchHelper.RIGHT){
                    int position = viewHolder.getAdapterPosition();
                    viewHolder.itemView.findViewById(R.id.ll4).setVisibility(View.VISIBLE);
                    rv.getAdapter().notifyDataSetChanged();
                }else{
                    viewHolder.itemView.findViewById(R.id.ll4).setVisibility(View.GONE);
                    rv.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
                return super.getSwipeThreshold(viewHolder);
            }
        }).attachToRecyclerView(rv);
        Observer();

    }
    @Override
    public void onBackPressed() {
        finish();
    }

    //observing live data
    private void Observer() {
        //getting team data such as manager name and tech stack associated from Team table
        mViewModel.teamData().observe(EmployeeListActivity.this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if(team!=null){
                    teamData=team;
                    tv1.setText(team.getName());
                    tv2.setText(team.getManager());
                    tv3.setText(team.getTech());
                }
            }
        });
        //Getting list of employees which are in the current team
        mViewModel.employeeList().observe(EmployeeListActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList=users;
                tv4.setText(""+userList.size());
                adapter=new EmployeeAdapter(EmployeeListActivity.this,userList);
                adapter.addItemClickListener(EmployeeListActivity.this);
                adapter.addItemClickListener2(EmployeeListActivity.this);
                rv.setAdapter(adapter);
            }
        });

        mViewModel.update2().observe(EmployeeListActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mViewModel.getEmployeeFromTeam(teamName);
            }
        });
    }

    //item click listener interface is overidden here to go to Planner Activity
    @Override
    public void onItemClick(int position) {
        Intent intent=new Intent(EmployeeListActivity.this,PlannerActivity.class);
        intent.putExtra("UserName",userList.get(position).getUserName());
        intent.putExtra("ViewOnly",true);
        startActivity(intent);
    }
    @Override
    public void onItemClick2(int position){
        mViewModel.removeTeamForEmployee(userList.get(position).getEmpNumber());
    }
}