package myBlog.storage;

import myBlog.exception.ModelNotFoundException;
import myBlog.ifS.PostStorage;
import myBlog.model.Post;
import myBlog.model.User;

public class PostStorageimpl extends Throwable implements PostStorage {
    private Post[] posts;
    private int size;

    public PostStorageimpl() {
        posts = new Post[15];
    }

    public PostStorageimpl(int temp) {
        posts = new Post[temp];
    }

    @Override
    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] posts1 = new Post[posts.length + 15];
        System.arraycopy(posts, 0, posts1, 0, posts.length);
        posts = posts1;
    }

    @Override
    public Post getPostByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        return null;
    }

    @Override
    public void searchPostsByKeyword(String keyword)  {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            }
        }

    }
    @Override
    public void printPostsByCategory(String category) throws ModelNotFoundException {
        for (int i = 0; i < size; i++) {
            if (category.equals(posts[i].getCategory())) {
                System.out.println(posts[i]);
            }
        } throw new ModelNotFoundException("Wrong Category!");
    }

    @Override
    public void printPostbyUsers(String email) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getUser().getEmail().equals(email)){
                System.out.println(posts[i]);
            }
        }
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }
    }
}
