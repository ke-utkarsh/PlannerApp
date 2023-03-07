package ymsli.com.adminemployee.database.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;


@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertUser(User user);

    @Delete
    public void deleteUser(User user);

    @Delete
    public  int deleteTeam(Team team);

    @Query("Select * from UserTable where userName =:userName")
    public User getUserById(String userName);

    @Query("Select * from UserTable where empNumber=:empNum")
    public User getUserByEid(String empNum);

    @Query("Select * from UserTable where userName =:userName and password =:password")
    public User getUserByNameAndPass(String userName,String password);

    @Query("Select * from UserTable where empNumber =:empNum and password =:password")
    public User getUserByEidAndPass(String empNum,String password);


    @Query("Select COUNT(userName) from UserTable where managerName=:uname")
    public int getEmployeeCount(String uname);

    @Update
    public int updateUser(User user);

    @Query("Update UserTable set managerName=:manager, currentTeam=:team where userName=:userName ")
    public int updateManager(String manager,String team, String userName);

    @Query("Update UserTable set managerName=:manager, currentTeam=:team where empNumber=:eid ")
    public int updateManagerByEid(String manager,String team, String eid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertTeam(Team team);

    @Query("Select COUNT(name) from TeamTable where manager=:uname")
    public int getTeamCount(String uname);

    @Query("Select * from TeamTable where manager=:uname")
    public List<Team> getTeams(String uname);

    @Query("Select * from UserTable where managerName=:uname")
    public List<User> getEmpCount(String uname);


    @Query("Select * from UserTable where currentTeam=:team")
    public List<User> getEmployees(String team);

    @Query("Select * from UserTable where currentTeam=:team and rights=:rights")
    public List<User> getFreeEmployees(String team, String rights);

    @Query("Select * from TeamTable where name=:team")
    public Team getTeam(String team);

    @Query("Update UserTable set dates=:dates where userName=:uname")
    public int updateDate(String uname,String dates);

    @Query("Update UserTable set managerName=:manager, currentTeam=:team where currentTeam=:currTeam")
    public int updateUserTeamDelete(String manager,String team, String currTeam);

    @Query("Update UserTable set managerName=:manager, currentTeam=:team where empNumber=:eid")
    public int removeTeamByEid(String manager,String team, String eid);
}
