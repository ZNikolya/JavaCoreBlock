package myBlog.ifS;

import myBlog.exception.PostNotFoundException;
import myBlog.model.Post;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title);

    void searchPostsByKeyword(String keyword) throws PostNotFoundException;

    void printAllPosts();

    void printPostsByCategory(String category) throws PostNotFoundException;

}
