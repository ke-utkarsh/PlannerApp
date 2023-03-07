package ymsli.com.adminemployee.network;

import java.util.List;

import javax.inject.Inject;

import ymsli.com.adminemployee.database.room.UserDatabase;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;

//repositiory is used for making database calls
public class Repository {
    private final UserDatabase userDatabase;

    @Inject
    public Repository(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;

    }

    //for inserting a new team
    public Long insertTeam(Team team){ return userDatabase.getUserDao().insertTeam(team);}

    //for inserting a new user
    public Long insertUser(User user){
        return userDatabase.getUserDao().insertUser(user);
    }

    //for login
    public User userLogin(String userName,String password){
        return userDatabase.getUserDao().getUserByEidAndPass(userName,password);
    }

    //to search user by name
    public User searchUserByName(String uName) {
        return  userDatabase.getUserDao().getUserById(uName);
    }
    //to search user by eid
    public User searchUserByEid(String eId){
        return userDatabase.getUserDao().getUserByEid(eId);
    }

    //to get number of employees under a given manager
    public int getMangerEmployeeCount(String uName){
        return userDatabase.getUserDao().getEmployeeCount(uName);
    }

    //to get number of teams under a manager
    public int getTeamCount(String uName){
        return userDatabase.getUserDao().getTeamCount(uName);
    }



    public int updateUserData(User user){
        return userDatabase.getUserDao().updateUser(user);
    }

    //to update manager data by managerName
    public int updateManagerData(String manager,String team, String userName){
        return userDatabase.getUserDao().updateManager(manager,team,userName);
    }

    //to update manager data by EIN
    public int updateManagerDataByEid(String manager,String team, String eid){
        return userDatabase.getUserDao().updateManagerByEid(manager,team,eid);
    }

    //to get list of teams managed by an admin
    public List<Team> getTeamData(String uname){
        return userDatabase.getUserDao().getTeams(uname);
    }

    //
    public List<User> getEmpCount(String uname){
        return userDatabase.getUserDao().getEmpCount(uname);
    }


    public List<User> getEmployeeData(String team){
        return userDatabase.getUserDao().getEmployees(team);
    }
    public List<User> getFreeEmployeeData(String team){
        return userDatabase.getUserDao().getFreeEmployees(team,"Employee");
    }


    public Team getTeam(String team){
        return userDatabase.getUserDao().getTeam(team);
    }

    public int updateDateData(String uname,String date){
        return userDatabase.getUserDao().updateDate(uname,date);
    }

    //to delete a team
    public int deleteTeam(Team team){
        return userDatabase.getUserDao().deleteTeam(team);
    }


    //to update team memebers of deleted team
    public int updateUserAfterDeleteTeamData(String currTeam){
        return userDatabase.getUserDao().updateUserTeamDelete("","",currTeam);
    }

    public int updateTeamByEin(String eid){
        return userDatabase.getUserDao().removeTeamByEid("","",eid);
    }

}
