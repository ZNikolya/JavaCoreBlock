package myBlog.interfaces;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.Post;
import myBlog.model.User;

public interface DataStorage {
    //Users
    User getUserByEMailAndPassword(String email, String password) throws ModelNotFoundException;

    User getUserByEmail(String email) throws ModelNotFoundException;

    //Posts
    Post getPostByTitle(String title) throws ModelNotFoundException;

    void searchPostsByKeyword(String keyword) throws ModelNotFoundException, PostStorageimpl;

    void printAllPosts();

    void printPostsByCategory(String category) throws ModelNotFoundException;

    void printPostbyUsers(String email);
}

