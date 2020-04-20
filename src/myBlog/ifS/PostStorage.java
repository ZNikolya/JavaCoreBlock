package myBlog.ifS;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.Post;
import myBlog.model.User;
import myBlog.storage.PostStorageimpl;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title) throws ModelNotFoundException;

    void searchPostsByKeyword(String keyword) throws ModelNotFoundException, PostStorageimpl;

    void printAllPosts();

    void printPostsByCategory(String category) throws ModelNotFoundException;

    void printPostbyUsers(String email);

}
