package ymsli.com.adminemployee.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.ui.view.adapter.EmployeeAdapter;
import ymsli.com.adminemployee.ui.viewModel.AddMemberViewModel;
import ymsli.com.adminemployee.ui.viewModel.CreateTeamViewModel;

@AndroidEntryPoint
public class AddMemberActivity extends AppCompatActivity {

    private AddMemberViewModel mViewModel;
    TextView tvManager,tvTeamName,tvTech;
    String teamName,manager,eid22;
    Button add;
    RecyclerView rv;
    ImageView ivBack;
    EmployeeAdapter adapter;
    ArrayList<String> einList;
    Spinner spn1;
    ArrayList<User> userList;
    int i=0;

    //initializing view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_member);

        spn1=findViewById(R.id.spnFree);
        add=findViewById(R.id.btnAdd);
        mViewModel = new ViewModelProvider(this).get(AddMemberViewModel.class);
        Bundle bundle = getIntent().getExtras();
        manager=bundle.getString("UserName");
        eid22=bundle.getString("Ein");
        teamName=bundle.getString("TeamName");
        tvManager=findViewById(R.id.tvE1);
        tvTeamName=findViewById(R.id.tvE2);
        tvTech=findViewById(R.id.tvE3);
        tvManager.setText(manager);
        tvTeamName.setText(teamName);
        tvTech.setText(bundle.getString("TechStack"));
        userList=new ArrayList<>();
        rv=findViewById(R.id.rv);
        ivBack=findViewById(R.id.ivBack);


        mViewModel.searchFreeMembers();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spn1.getSelectedItem().toString().contains("Select Team Member")){
                    Toasty.error(AddMemberActivity.this,"Please select an employee",Toast.LENGTH_SHORT).show();
                }else{
                    mViewModel.updateManagerByEid(eid22,teamName,spn1.getSelectedItem().toString());
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AddMemberActivity.this,HomeActivity.class);
                intent.putExtra("UserName",manager);
                intent.putExtra("Ein",eid22);
                startActivity(intent);
            }
        });


        Observer();
    }

    //to handle back presses
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(AddMemberActivity.this,HomeActivity.class); //go to Home Activity
        intent.putExtra("UserName",manager);
        intent.putExtra("Ein",eid22);
        startActivity(intent);
    }

    //For observing live data
    private void Observer() {
        //for getting spinner data
        mViewModel.employeeList().observe(AddMemberActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                einList=new ArrayList<>();
                einList.add("Select Team Member");
                if(users!=null){
                    for(User i:users){
                        einList.add(i.getEmpNumber());
                    }
                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(AddMemberActivity.this, android.R.layout.simple_spinner_item,einList);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spn1.setAdapter(adapter2);
            }
        });


        //for updating tables to add new member
        mViewModel.update().observe(AddMemberActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Toast.makeText(AddMemberActivity.this, "Employee added successfully", Toast.LENGTH_SHORT).show();
                    i+=1;
                    TextView tv=(TextView) findViewById(R.id.counter);
                    tv.setText("Members Added: "+i);
                    mViewModel.searchFreeMembers();
                    mViewModel.getEmployeeFromTeam(teamName);
                }
            }
        });

        //for bottom Recycler View
        mViewModel.employeeList1().observe(AddMemberActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList= (ArrayList<User>) users;
                adapter=new EmployeeAdapter(AddMemberActivity.this,userList);
                rv.setAdapter(adapter);
            }
        });
    }
}