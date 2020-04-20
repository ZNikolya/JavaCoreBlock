package myBlog.storage;

import myBlog.exception.ModelNotFoundException;
import myBlog.interfaces.UserStorrage;
import myBlog.model.User;


public class UserStorageimpl implements UserStorrage {

    private User [] users;
    private int size = 0;

    public UserStorageimpl(){
        users = new User[15];
    }

    public UserStorageimpl(int temp){
        users = new User[temp];
    }

    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        users[size++] = user;
    }
    private void extend() {
        User[] users1 = new User[users.length + 10];
        System.arraycopy(users,0,users1,0,users.length);
        users = users1;
    }

    @Override
    public User getUserByEMailAndPassword(String email, String password) throws ModelNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)){
                return users[i];
            }
        }
        throw new ModelNotFoundException("Wrong email or password!!!");
    }

    @Override
    public void printUsers() {

    }

    @Override
    public User getUserByEmail(String email)  {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)){
                return users[i];
            }
        }
       return null;
    }


}
