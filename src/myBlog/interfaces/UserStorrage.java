package myBlog.interfaces;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.User;

public interface UserStorrage {

    void add(User user);
    User getUserByEMailAndPassword(String email, String password) throws ModelNotFoundException;
    void printUsers();
    User getUserByEmail(String email) throws ModelNotFoundException;

}
