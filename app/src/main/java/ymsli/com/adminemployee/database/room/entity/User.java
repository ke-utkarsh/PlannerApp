package ymsli.com.adminemployee.database.room.entity;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

//Making an entity for user
@Entity(tableName = "UserTable")
public class User {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String empNumber;
    @NonNull
    private String userName;
    private String password;
    private String rights;
    private String managerName;
    private String currentTeam;
    private String dates;

    public User(@NonNull String empNumber,@NonNull String userName, String password, String rights, String managerName, String currentTeam, String dates) {
        this.empNumber=empNumber;
        this.userName = userName;
        this.password = password;
        this.rights = rights;
        this.managerName = managerName;
        this.currentTeam = currentTeam;
        this.dates = dates;
    }

    @NonNull
    public  String getEmpNumber(){
        return empNumber;
    }

    public void setEmpNumber(@NonNull String empNumber){
        this.empNumber=empNumber;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }
}
