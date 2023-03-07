package ymsli.com.adminemployee.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import es.dmoral.toasty.Toasty;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.ui.viewModel.CreateTeamViewModel;
import ymsli.com.adminemployee.ui.viewModel.HomeViewModel;

@AndroidEntryPoint
public class CreateTeamActivity extends AppCompatActivity {
    private CreateTeamViewModel mViewModel;
    TextInputEditText teamName;
    String manager,eid;
    Spinner spn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_team);
        mViewModel = new ViewModelProvider(this).get(CreateTeamViewModel.class);
        Bundle bundle = getIntent().getExtras();
        manager=bundle.getString("UserName");
        eid=bundle.getString("Ein");
        teamName=findViewById(R.id.tvTeamName);
        spn1=findViewById(R.id.spnTech);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.tech, android.R.layout.simple_spinner_item);
        spn1.setAdapter(adapter2);
        spn1.setSelection(0);
        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidations()){
                    mViewModel.getTeamData(teamName.getText().toString());
                }
            }
        });

        observer();
    }
    @Override
    public void onBackPressed() {
        finish();
    }

    //validating team name for empty string and string size<3
    private boolean checkValidations(){
        if(teamName.getText().toString().length()==0){
            Toasty.error(CreateTeamActivity.this,"Team Name can't be empty",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(teamName.getText().toString().trim().length()<3){
            Toasty.error(CreateTeamActivity.this,"Team name must be atleast 3 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void observer() {

        //to check if team name choosen doesn't already exist
        mViewModel.teamData().observe(CreateTeamActivity.this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if(team!=null){
                    Toasty.error(CreateTeamActivity.this,"Choose a different team name",Toast.LENGTH_SHORT).show();
                }else{
                    mViewModel.insertTeam(new Team(teamName.getText().toString(),spn1.getSelectedItem().toString(),eid));
                }
            }
        });

        //Team created successfully now go to add team members
        mViewModel.isRegister().observe(CreateTeamActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mViewModel.searchFreeMembers();

                }
            }
        });

        mViewModel.employeeList().observe(CreateTeamActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users.size()==0){
                    Toasty.info(CreateTeamActivity.this,"No employees available",Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Intent intent = new Intent(CreateTeamActivity.this, AddMemberActivity.class);
                    intent.putExtra("UserName",manager);
                    intent.putExtra("Ein",eid);
                    intent.putExtra("TeamName",teamName.getText().toString());
                    intent.putExtra("TechStack",spn1.getSelectedItem().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}