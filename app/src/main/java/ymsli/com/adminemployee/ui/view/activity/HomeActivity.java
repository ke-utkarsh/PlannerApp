package ymsli.com.adminemployee.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.database.sharefpref.SessionManagement;
import ymsli.com.adminemployee.ui.view.adapter.TeamAdapter;
import ymsli.com.adminemployee.ui.viewModel.HomeViewModel;
import ymsli.com.adminemployee.ui.viewModel.LoginViewModel;


//Home Activity implementing 3 click listeners
@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity implements TeamAdapter.ItemClickListener, TeamAdapter.ItemClickListener1,TeamAdapter.ItemClickListener2 {
    private HomeViewModel mViewModel;
    private String uname="",eid="";
    private TextView tvname,tvEin;
    public TextView nteam;
    public TextView nEmp;
    public User user;
    public RecyclerView rv;
    public List<Team> t;
    private Dialog dialog,dialog2;

    TeamAdapter adapter;
    Team currteam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        Bundle bundle = getIntent().getExtras();
        uname=bundle.getString("UserName");
        eid=bundle.getString("Ein");
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        mViewModel.getEmpCountData(eid);
        mViewModel.countTeams(eid);
        mViewModel.getTeamData(eid);

        nEmp=findViewById(R.id.tvEmployee);
        nteam=findViewById(R.id.tvTeam);
        tvname=findViewById(R.id.tvName);
        tvEin=findViewById(R.id.tvEin);
        tvname.setText("Welcome "+uname+",");
        tvEin.setText(eid);
        rv=findViewById(R.id.rvTeams);
        //rv.setAdapter(new TeamAdapter(this,));


        findViewById(R.id.ivLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement = new SessionManagement(HomeActivity.this);
                sessionManagement.saveSession("","");
                Intent intent=new Intent(HomeActivity.this,LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CreateTeamActivity.class);
                intent.putExtra("UserName",uname);
                intent.putExtra("Ein",eid);
                startActivity(intent);
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
                    viewHolder.itemView.findViewById(R.id.ll2).setVisibility(View.VISIBLE);
                    rv.getAdapter().notifyDataSetChanged();
                }else{
                    viewHolder.itemView.findViewById(R.id.ll2).setVisibility(View.GONE);
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



    private void Observer(){
        //getting data from teams table to get number of teams of a particular admin
        mViewModel.team().observe(HomeActivity.this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                Log.e("Adapter", String.valueOf(teams.size()));
                nteam.setText("No. of teams: "+teams.size());
                t=teams;
                adapter=new TeamAdapter(HomeActivity.this,teams);
                adapter.addItemClickListener(HomeActivity.this);
                adapter.addItemClickListener1(HomeActivity.this);
                adapter.addItemClickListener2(HomeActivity.this);
                rv.setAdapter(adapter);

            }
        });

        //counting the number of employees under a admin
        mViewModel.count().observe(HomeActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                nEmp.setText("No. of employees: "+integer);
            }
        });


        //counting number of teams of an Admin
        mViewModel.teamcount().observe(HomeActivity.this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Toast.makeText(HomeActivity.this, integer, Toast.LENGTH_SHORT).show();
                nteam.setText("No. of teams: "+integer);
            }
        });

        //checking if entered ein is correct or not
        mViewModel.isAlreadyRegistered().observe(HomeActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user==null){
                    Toasty.error(HomeActivity.this, "User doesn't exist", Toast.LENGTH_SHORT).show();
                }else{
                    if(user.getCurrentTeam().length()==0){
                        if(user.getRights().contains("Employee")){
                            mViewModel.updateManagerByEid(currteam.getManager(),currteam.getName(),user.getEmpNumber());
                        }else{
                            Toasty.error(HomeActivity.this,"Admin level employees can't be added!!",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        if(user.getManagerName().contains(eid)){
                            Toasty.info(HomeActivity.this,"This employee is already in the team",Toast.LENGTH_SHORT).show();
                        }else{
                            Toasty.error(HomeActivity.this,"This employee is already in different team",Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        //updating the team and entered ein for that team and corresponding manager
        mViewModel.update().observe(HomeActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    dialog.cancel();
                    Toasty.success(HomeActivity.this, "Employee added to "+currteam.getName()+" successfully!!", Toast.LENGTH_SHORT).show();
                }else{
                    dialog.cancel();
                    Toasty.error(HomeActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                }
                mViewModel.getTeamData(eid);
                mViewModel.getEmpCountData(eid);
            }
        });
        //checking if team is deleted from Teams table or not
        mViewModel.delete().observe(HomeActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mViewModel.updateUserAfterTeamDeletion(currteam.getName());
                }else{
                    Toasty.error(HomeActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //updating deleted team members
        mViewModel.update2().observe(HomeActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                mViewModel.getTeamData(eid);
                mViewModel.getEmpCountData(eid);
                mViewModel.countTeams(eid);
                Toasty.success(HomeActivity.this,"Team Deleted Successfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //item click listener overrided for goting to EmployeeListActivity
    @Override
    public void onItemClick(int position) {

        Intent intent=new Intent(HomeActivity.this,EmployeeListActivity.class);
        intent.putExtra("UserName",t.get(position).getName());
        intent.putExtra("Ein",eid);
        startActivity(intent);
    }

    //click listener for showing delete dialog
    @Override
    public void onItemClick1(int position) {
        currteam=t.get(position);
        dialog2= new Dialog(HomeActivity.this,R.style.DialogStyle);
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog2.setCancelable(true);
        dialog2.setContentView(R.layout.dialog_delete);
        dialog2.show();

        dialog2.findViewById(R.id.tvCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.deleteTeam(currteam);
                dialog2.cancel();
            }
        });
        dialog2.findViewById(R.id.tvSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.cancel();
            }
        });
    }
    //click listener for showing add member dialog
    @Override
    public void onItemClick2(int position) {
        currteam=t.get(position);
        dialog= new Dialog(HomeActivity.this,R.style.DialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_add_member);
        dialog.show();
        EditText et=dialog.findViewById(R.id.etName);
        dialog.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et.getText().toString().trim().length()==0){
                    Toasty.error(HomeActivity.this,"Ein can't be empty",Toast.LENGTH_SHORT).show();
                }
                else if(!et.getText().toString().trim().startsWith("YM")||et.getText().toString().length()<3){
                    Toasty.error(HomeActivity.this,"Wrong format of Ein",Toast.LENGTH_SHORT).show();
                } else{
                    mViewModel.searchUserByEid(et.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.getEmpCountData(eid);
        mViewModel.countTeams(eid);
        mViewModel.getTeamData(eid);
    }
}