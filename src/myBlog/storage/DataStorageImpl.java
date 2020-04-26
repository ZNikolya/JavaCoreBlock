package myBlog.storage;
import myBlog.exception.ModelNotFoundException;
import myBlog.interfaces.DataStorage;
import myBlog.model.Post;
import myBlog.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorageImpl implements DataStorage {

    List<User> users;
    List<Post> posts;

    public DataStorageImpl() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public DataStorageImpl(int lenght) {
        users = new ArrayList<>(lenght);
        posts = new ArrayList<>(lenght);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }


    @Override
    public User getUserByEMailAndPassword(String email, String password) throws ModelNotFoundException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }
        throw new ModelNotFoundException("Wrong email or password!!!");
    }

    @Override
    public User getUserByEmail(String email) throws ModelNotFoundException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public Post getPostByTitle(String title) throws ModelNotFoundException {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle().equals(title)) {
                return posts.get(i);
            }
        }
        return null;
    }

    @Override
    public void searchPostsByKeyword(String keyword) throws ModelNotFoundException {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle().contains(keyword) || posts.get(i).getText().contains(keyword)) {
                System.out.println(posts.get(i));
            }
        }

    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i));
        }
    }

    @Override
    public void printPostsByCategory(String category) throws ModelNotFoundException {
        for (int i = 0; i < posts.size(); i++) {
            if (category.equals(posts.get(i).getCategory())) {
                System.out.println(posts.get(i));
            }
        }
    }

    @Override
    public void printPostbyUsers(String email) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getUser().getEmail().equals(email)) {
                System.out.println(posts.get(i));
            }
        }
    }
}