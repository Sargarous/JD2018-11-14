package by.it.kruglenja.TaskManager.java.dao;

import by.it.kruglenja.TaskManager.java.beans.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements InterfaceDAO<User> {

    public boolean create(User user) throws SQLException {
        String sql = String.format("INSERT INTO `user`( `login`, `userPassword`, `userEmail`, `Roles_id`) VALUES ('%s', '%s', '%s', '%s', '%d')",
                user.getlogin(), user.getUserPassword(), user.getUserEmail(), user.getRoles_id());
        long id = Dao.executeCrate(sql);
        user.setId(id);
        return id > 0;
    }

    public User read(long id) throws SQLException {
        List<User> users = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public boolean update(User user) throws SQLException {
        String sql = String.format("UPDATE `user` SET `login` = '%s', `userPassword`= '%s',`userEmail` = '%s'," +
                        "`Roles_id` = '%d' WHERE `user`.`id` = '%d'", user.getlogin(), user.getUserPassword(),
                user.getUserEmail(), user.getRoles_id());
        return Dao.executeUpdate(sql);
    }

    @Override
    public List<User> getAll(String sqlSuffix) throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = String.format()
        return user;
    }
}
