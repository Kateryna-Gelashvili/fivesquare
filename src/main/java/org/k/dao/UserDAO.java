package org.k.dao;

import org.k.domain.Checking;
import org.k.domain.Comment;
import org.k.domain.User;

import java.util.List;
import java.util.Set;

public interface UserDAO {
    public int addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public List<User> getAllUsers();
    public User getUserByID(int id);
    public User getUserByName(String name);
    public Set<Checking> getCheckings(User user);
    public Set<Comment> getComments(User user);
}
