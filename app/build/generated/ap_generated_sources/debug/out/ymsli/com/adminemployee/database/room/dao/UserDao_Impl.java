package ymsli.com.adminemployee.database.room.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ymsli.com.adminemployee.database.room.entity.Team;
import ymsli.com.adminemployee.database.room.entity.User;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final EntityInsertionAdapter<Team> __insertionAdapterOfTeam;

  private final EntityDeletionOrUpdateAdapter<User> __deletionAdapterOfUser;

  private final EntityDeletionOrUpdateAdapter<Team> __deletionAdapterOfTeam;

  private final EntityDeletionOrUpdateAdapter<User> __updateAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateManager;

  private final SharedSQLiteStatement __preparedStmtOfUpdateManagerByEid;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDate;

  private final SharedSQLiteStatement __preparedStmtOfUpdateUserTeamDelete;

  private final SharedSQLiteStatement __preparedStmtOfRemoveTeamByEid;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `UserTable` (`empNumber`,`userName`,`password`,`rights`,`managerName`,`currentTeam`,`dates`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.getEmpNumber() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmpNumber());
        }
        if (value.getUserName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserName());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getRights() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRights());
        }
        if (value.getManagerName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getManagerName());
        }
        if (value.getCurrentTeam() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCurrentTeam());
        }
        if (value.getDates() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDates());
        }
      }
    };
    this.__insertionAdapterOfTeam = new EntityInsertionAdapter<Team>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `TeamTable` (`name`,`tech`,`manager`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Team value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
        if (value.getTech() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTech());
        }
        if (value.getManager() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getManager());
        }
      }
    };
    this.__deletionAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `UserTable` WHERE `empNumber` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.getEmpNumber() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmpNumber());
        }
      }
    };
    this.__deletionAdapterOfTeam = new EntityDeletionOrUpdateAdapter<Team>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `TeamTable` WHERE `name` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Team value) {
        if (value.getName() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getName());
        }
      }
    };
    this.__updateAdapterOfUser = new EntityDeletionOrUpdateAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `UserTable` SET `empNumber` = ?,`userName` = ?,`password` = ?,`rights` = ?,`managerName` = ?,`currentTeam` = ?,`dates` = ? WHERE `empNumber` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        if (value.getEmpNumber() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getEmpNumber());
        }
        if (value.getUserName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserName());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getRights() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getRights());
        }
        if (value.getManagerName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getManagerName());
        }
        if (value.getCurrentTeam() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getCurrentTeam());
        }
        if (value.getDates() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDates());
        }
        if (value.getEmpNumber() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getEmpNumber());
        }
      }
    };
    this.__preparedStmtOfUpdateManager = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update UserTable set managerName=?, currentTeam=? where userName=? ";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateManagerByEid = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update UserTable set managerName=?, currentTeam=? where empNumber=? ";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update UserTable set dates=? where userName=?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateUserTeamDelete = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update UserTable set managerName=?, currentTeam=? where currentTeam=?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveTeamByEid = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "Update UserTable set managerName=?, currentTeam=? where empNumber=?";
        return _query;
      }
    };
  }

  @Override
  public Long insertUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfUser.insertAndReturnId(user);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Long insertTeam(final Team team) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfTeam.insertAndReturnId(team);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteTeam(final Team team) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__deletionAdapterOfTeam.handle(team);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateUser(final User user) {
    __db.assertNotSuspendingTransaction();
    int _total = 0;
    __db.beginTransaction();
    try {
      _total +=__updateAdapterOfUser.handle(user);
      __db.setTransactionSuccessful();
      return _total;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int updateManager(final String manager, final String team, final String userName) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateManager.acquire();
    int _argIndex = 1;
    if (manager == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, manager);
    }
    _argIndex = 2;
    if (team == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, team);
    }
    _argIndex = 3;
    if (userName == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, userName);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateManager.release(_stmt);
    }
  }

  @Override
  public int updateManagerByEid(final String manager, final String team, final String eid) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateManagerByEid.acquire();
    int _argIndex = 1;
    if (manager == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, manager);
    }
    _argIndex = 2;
    if (team == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, team);
    }
    _argIndex = 3;
    if (eid == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, eid);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateManagerByEid.release(_stmt);
    }
  }

  @Override
  public int updateDate(final String uname, final String dates) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDate.acquire();
    int _argIndex = 1;
    if (dates == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, dates);
    }
    _argIndex = 2;
    if (uname == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, uname);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateDate.release(_stmt);
    }
  }

  @Override
  public int updateUserTeamDelete(final String manager, final String team, final String currTeam) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateUserTeamDelete.acquire();
    int _argIndex = 1;
    if (manager == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, manager);
    }
    _argIndex = 2;
    if (team == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, team);
    }
    _argIndex = 3;
    if (currTeam == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, currTeam);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateUserTeamDelete.release(_stmt);
    }
  }

  @Override
  public int removeTeamByEid(final String manager, final String team, final String eid) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveTeamByEid.acquire();
    int _argIndex = 1;
    if (manager == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, manager);
    }
    _argIndex = 2;
    if (team == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, team);
    }
    _argIndex = 3;
    if (eid == null) {
      _stmt.bindNull(_argIndex);
    } else {
      _stmt.bindString(_argIndex, eid);
    }
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveTeamByEid.release(_stmt);
    }
  }

  @Override
  public User getUserById(final String userName) {
    final String _sql = "Select * from UserTable where userName =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userName);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _result = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByEid(final String empNum) {
    final String _sql = "Select * from UserTable where empNumber=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (empNum == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, empNum);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _result = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByNameAndPass(final String userName, final String password) {
    final String _sql = "Select * from UserTable where userName =? and password =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userName == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userName);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _result = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getUserByEidAndPass(final String empNum, final String password) {
    final String _sql = "Select * from UserTable where empNumber =? and password =?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (empNum == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, empNum);
    }
    _argIndex = 2;
    if (password == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, password);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final User _result;
      if(_cursor.moveToFirst()) {
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _result = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getEmployeeCount(final String uname) {
    final String _sql = "Select COUNT(userName) from UserTable where managerName=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uname == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uname);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getTeamCount(final String uname) {
    final String _sql = "Select COUNT(name) from TeamTable where manager=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uname == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uname);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Team> getTeams(final String uname) {
    final String _sql = "Select * from TeamTable where manager=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uname == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uname);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfTech = CursorUtil.getColumnIndexOrThrow(_cursor, "tech");
      final int _cursorIndexOfManager = CursorUtil.getColumnIndexOrThrow(_cursor, "manager");
      final List<Team> _result = new ArrayList<Team>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Team _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpTech;
        if (_cursor.isNull(_cursorIndexOfTech)) {
          _tmpTech = null;
        } else {
          _tmpTech = _cursor.getString(_cursorIndexOfTech);
        }
        final String _tmpManager;
        if (_cursor.isNull(_cursorIndexOfManager)) {
          _tmpManager = null;
        } else {
          _tmpManager = _cursor.getString(_cursorIndexOfManager);
        }
        _item = new Team(_tmpName,_tmpTech,_tmpManager);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getEmpCount(final String uname) {
    final String _sql = "Select * from UserTable where managerName=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (uname == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, uname);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _item = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getEmployees(final String team) {
    final String _sql = "Select * from UserTable where currentTeam=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (team == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, team);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _item = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<User> getFreeEmployees(final String team, final String rights) {
    final String _sql = "Select * from UserTable where currentTeam=? and rights=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (team == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, team);
    }
    _argIndex = 2;
    if (rights == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, rights);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmpNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "empNumber");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "userName");
      final int _cursorIndexOfPassword = CursorUtil.getColumnIndexOrThrow(_cursor, "password");
      final int _cursorIndexOfRights = CursorUtil.getColumnIndexOrThrow(_cursor, "rights");
      final int _cursorIndexOfManagerName = CursorUtil.getColumnIndexOrThrow(_cursor, "managerName");
      final int _cursorIndexOfCurrentTeam = CursorUtil.getColumnIndexOrThrow(_cursor, "currentTeam");
      final int _cursorIndexOfDates = CursorUtil.getColumnIndexOrThrow(_cursor, "dates");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final String _tmpEmpNumber;
        if (_cursor.isNull(_cursorIndexOfEmpNumber)) {
          _tmpEmpNumber = null;
        } else {
          _tmpEmpNumber = _cursor.getString(_cursorIndexOfEmpNumber);
        }
        final String _tmpUserName;
        if (_cursor.isNull(_cursorIndexOfUserName)) {
          _tmpUserName = null;
        } else {
          _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        }
        final String _tmpPassword;
        if (_cursor.isNull(_cursorIndexOfPassword)) {
          _tmpPassword = null;
        } else {
          _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        }
        final String _tmpRights;
        if (_cursor.isNull(_cursorIndexOfRights)) {
          _tmpRights = null;
        } else {
          _tmpRights = _cursor.getString(_cursorIndexOfRights);
        }
        final String _tmpManagerName;
        if (_cursor.isNull(_cursorIndexOfManagerName)) {
          _tmpManagerName = null;
        } else {
          _tmpManagerName = _cursor.getString(_cursorIndexOfManagerName);
        }
        final String _tmpCurrentTeam;
        if (_cursor.isNull(_cursorIndexOfCurrentTeam)) {
          _tmpCurrentTeam = null;
        } else {
          _tmpCurrentTeam = _cursor.getString(_cursorIndexOfCurrentTeam);
        }
        final String _tmpDates;
        if (_cursor.isNull(_cursorIndexOfDates)) {
          _tmpDates = null;
        } else {
          _tmpDates = _cursor.getString(_cursorIndexOfDates);
        }
        _item = new User(_tmpEmpNumber,_tmpUserName,_tmpPassword,_tmpRights,_tmpManagerName,_tmpCurrentTeam,_tmpDates);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Team getTeam(final String team) {
    final String _sql = "Select * from TeamTable where name=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (team == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, team);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfTech = CursorUtil.getColumnIndexOrThrow(_cursor, "tech");
      final int _cursorIndexOfManager = CursorUtil.getColumnIndexOrThrow(_cursor, "manager");
      final Team _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpTech;
        if (_cursor.isNull(_cursorIndexOfTech)) {
          _tmpTech = null;
        } else {
          _tmpTech = _cursor.getString(_cursorIndexOfTech);
        }
        final String _tmpManager;
        if (_cursor.isNull(_cursorIndexOfManager)) {
          _tmpManager = null;
        } else {
          _tmpManager = _cursor.getString(_cursorIndexOfManager);
        }
        _result = new Team(_tmpName,_tmpTech,_tmpManager);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
