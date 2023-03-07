package ymsli.com.adminemployee.ui.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;
import ymsli.com.adminemployee.R;
import ymsli.com.adminemployee.database.room.entity.CalendarModel;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;
import ymsli.com.adminemployee.database.sharefpref.SessionManagement;
import ymsli.com.adminemployee.ui.view.adapter.GridCalAdapter;
import ymsli.com.adminemployee.ui.viewModel.HomeViewModel;
import ymsli.com.adminemployee.ui.viewModel.PlannerViewModel;


//this is the planner activity
@AndroidEntryPoint
public class PlannerActivity extends AppCompatActivity {
    GridView gvCal;
    DateFormat dateFormat;
    Date date;
    PlannerViewModel mViewModel;
    Spinner spn1,spn2;
    int size;
    @NonNull
    public static String uname="",leaves="";
    TextView t1,t2,t3,t4;
    boolean firstRun=true,viewOnly;
    public static ArrayList<CalendarModel> days;
    public static int[] months = {31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int today, beginOfMonth;
    String month, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_planner);

        Bundle bundle = getIntent().getExtras();
        uname=bundle.getString("UserName");
        viewOnly=bundle.getBoolean("ViewOnly");
        mViewModel = new ViewModelProvider(this).get(PlannerViewModel.class);

        mViewModel.searchUserByName(uname);

        //initializing year and month spinners
        spn1=findViewById(R.id.spnYear);
        spn2=findViewById(R.id.spnMonth);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(adapter1);
        spn2.setAdapter(adapter2);

        spn2.setSelection(2);
        spn1.setSelection(3);
        //click listener for year spinner
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = adapterView.getAdapter().getItem(i).toString();
                getDays(month,year);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //click listener for month spinner
        spn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getAdapter().getItem(i).toString();
                getDays(month,year);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //initializing gridview and setting days of month, first day of month
        gvCal = (GridView) findViewById(R.id.gv);
        dateFormat = new SimpleDateFormat("yyyy");
        date = new Date();
        months[1] = Feb(Integer.parseInt(dateFormat.format(date))); // Find the amount of days in Feb
        dateFormat = new SimpleDateFormat("MM");
        int numDays = months[Integer.parseInt(dateFormat.format(date))-1] + 6; // Number of days in the month as well as making sure not to override the day names
        // Check which day of the month the month started on. Eg: April 1st 2016 is a Friday
        dateFormat = new SimpleDateFormat("MM");
        month = dateFormat.format(date);
        dateFormat = new SimpleDateFormat("yyyy");
        year = dateFormat.format(date);

        t1=findViewById(R.id.tvE1);
        t2=findViewById(R.id.tvE2);
        t3=findViewById(R.id.tvE3);
        t4=findViewById(R.id.tvE4);

        if(viewOnly){
            findViewById(R.id.ivLogout).setVisibility(View.GONE);
            findViewById(R.id.ivBack).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.ivBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.ivLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionManagement sessionManagement = new SessionManagement(PlannerActivity.this);
                sessionManagement.saveSession("","");
                Intent intent=new Intent(PlannerActivity.this,LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


//        dateFormat = new SimpleDateFormat("dd");
//        String temp = dateFormat.format(date);
//        today = Integer.parseInt(temp);



        //click listener for gridview
        gvCal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!viewOnly){ //as this actiivty can be viewOnly also hence this check is provided
                    String newLeave=year;
                    if(month.length()==1)newLeave+="0"+month;
                    else newLeave+=month;
                    if((i-beginOfMonth-5)<10)newLeave+="0"+(i-beginOfMonth-5);
                    else newLeave+=""+(i-beginOfMonth-5);

                    if(days.get(i).isSelected()){
                        //days.get(i).setSelected(false);
                        leaves=leaves.replace(newLeave,"");
                        mViewModel.updateLeaveDates(uname,leaves);
                    }else{
                        leaves+=newLeave+";";
                        mViewModel.updateLeaveDates(uname,leaves);
                        //days.get(i).setSelected(true);
                    }
                }

            }
        });

        Observer();

    }

    private void Observer() {
        //getting user data such as leaves
        mViewModel.isAlreadyRegistered().observe(PlannerActivity.this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Toast.makeText(PlannerActivity.this, user.getDates(), Toast.LENGTH_SHORT).show();
                if(user.getDates()==null)leaves="";
                else leaves=user.getDates();
                getDays(month,year);
                if(firstRun){
                    t1.setText(user.getUserName());
                    t2.setText(user.getManagerName());
                    t3.setText(user.getCurrentTeam());
                    if(user.getCurrentTeam()!=null){
                        mViewModel.getTeamData(user.getCurrentTeam());
                    }else{
                        t4.setText("No tech assigned");
                        t3.setText("No team assigned");
                        t2.setText("No manager assigned");
                    }
                }
                firstRun=false;
            }
        });
        //to get team data and setting values at the top of the screen
        mViewModel.teamData().observe(PlannerActivity.this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                if(team!=null){
                    if(team.getTech()!=null){
                        t4.setText(""+team.getTech());
                    }
                }
            }
        });

        //for updates leaves
        mViewModel.dateUpdate().observe(PlannerActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.e("Update has","run");
                if(aBoolean) mViewModel.searchUserByName(uname);
                else Toast.makeText(PlannerActivity.this, "Leave might not be updated in database!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //main function for setting data in gridview
    public void getDays(String m,String y){
        try {
            beginOfMonth = (Day("01"+m+y))-1; // Get the beginning of the month (-1 because Android recognizes Sunday as the first day)
        } catch (ParseException pe) {
            Toast.makeText(getApplicationContext(), pe.getMessage(), Toast.LENGTH_LONG).show();
        }
        if (beginOfMonth == 0) {
            beginOfMonth = 7;
        }
        size=months[Integer.parseInt(m)-1];
        days=new ArrayList<>();
        //settings days of week
        days.add(new CalendarModel("Mon",false));
        days.add(new CalendarModel("Tue",false));
        days.add(new CalendarModel("Wed",false));
        days.add(new CalendarModel("Thu",false));
        days.add(new CalendarModel("Fri",false));
        days.add(new CalendarModel("Sat",false));
        days.add(new CalendarModel("Sun",false));

        //for setting empty boxes if 1st of that month isn't monday
        if(beginOfMonth != 0) {
            for (int i = 7; i <= (5 + beginOfMonth); i++) {
                days.add(new CalendarModel("",false));
            }
        }

        //for setting days of month
        for (int i = (6+beginOfMonth); i <= (size+beginOfMonth-1+6); i++) {
            //checking if current date is a leave date or not by making a checkdate parameter
            String checkDate=y;
            if(month.length()==1)checkDate+="0"+m;
            else checkDate+=m;
            if((i-beginOfMonth-5)<10)checkDate+="0"+(i-beginOfMonth-5);
            else checkDate+=""+(i-beginOfMonth-5);

            if(leaves!=null){
                if(leaves.contains(checkDate)){
                    days.add(new CalendarModel(Integer.toString(i-beginOfMonth-5),true)); //setting leave day
                }else{
                    if((days.size()+2)%7==0||(days.size()+1)%7==0){ //for setting weekends to red
                        days.add(new CalendarModel(Integer.toString(i-beginOfMonth-5),true));
                    }else{
                        days.add(new CalendarModel(Integer.toString(i-beginOfMonth-5),false)); //weekdays which are not leaves
                    }
                }
            }else{ //if there are no leaves this code will run
                if((days.size()+2)%7==0||(days.size()+1)%7==0){ //for setting weekends to red
                    days.add(new CalendarModel(Integer.toString(i-beginOfMonth-5),true));
                }else{
                    days.add(new CalendarModel(Integer.toString(i-beginOfMonth-5),false)); //weekdays
                }
            }


        }

        //initializing gridview and setting adapter
        GridCalAdapter adapter=new GridCalAdapter(PlannerActivity.this,days);
        gvCal.setAdapter(adapter);
    }

    public void completeCalendar(){

    }
    public int Feb(int year) {
        int temp;
        try {
            temp = year / 4;
        } catch (Exception e) {
            return 28;
        }
        return 29;
    }

    public int Day(String day) throws ParseException {
        DateFormat df = new SimpleDateFormat("ddMMyyyy");
        try {
            Date d = df.parse(String.valueOf(day));
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
            ParseException pe = new ParseException("There was a problem getting the date.", 0);
            throw pe;
        }
    }

    public View GetViewByPosition(int position) {
        int firstPosition = gvCal.getFirstVisiblePosition();
        int lastPosition = gvCal.getLastVisiblePosition();

        if ((position < firstPosition) || (position > lastPosition))
            return null;

        return gvCal.getChildAt(position - firstPosition);
    }
}