package myBlog;

import myBlog.exception.PostNotFoundException;
import myBlog.ifS.Commands;
import myBlog.model.Post;
import myBlog.storage.PostStorageimpl;

import java.util.Date;
import java.util.Scanner;

public class RunBlog implements Commands {

    static Scanner scanner = new Scanner(System.in);
    static Post post = new Post();
    static PostStorageimpl postStorageimpl = new PostStorageimpl();

    public static void main(String[] args) {
        boolean search = true;
        while (search) {
            printCommands();
            String commandStr = scanner.nextLine();
            int command;
            try {
                command = Integer.parseInt(commandStr);
            } catch (Exception e) {
                command = -1;
            }

            switch (command) {
                case EXIT:
                    search = false;
                    System.out.println("Good by");
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_POST:
                    searchpost();
                    break;
                case POST_BY_CATEGORY:
                    postByCategory();
                    break;
                case ALL_POST:
                    postStorageimpl.printAllPosts();
                    break;
                default:
                    System.out.println("Warning");
            }

        }
    }

    private static void postByCategory() {
        System.out.println("Please input Post category");
        try {
            String category = scanner.nextLine();
            postStorageimpl.printPostsByCategory(category);
        }catch (PostNotFoundException e){
            System.out.println(e.getMessage());
            postByCategory();
        }
    }

    private static void searchpost() {
        System.out.println("Write the word you want to search for");
        try {
            String search = scanner.nextLine();
            postStorageimpl.searchPostsByKeyword(search);
        }catch (PostNotFoundException e){
            System.out.println(e.getMessage());
            searchpost();
        }
    }

    private static void addPost() {
        try {
            System.out.println("Please input Post data: title,text,category");
            String postDataStr = scanner.nextLine();
            String[] postData = postDataStr.split(",");
            Post byTitle = postStorageimpl.getPostByTitle(postData[0]);
            if (byTitle != null) {
                System.out.println("Duplicate Title");
                addPost();
            } else {
                Post post = new Post();
                Date date = new Date();
                post.setTitle(postData[0]);
                post.setText(postData[1]);
                post.setCategory(postData[2]);
                post.setCreatedData(date);
                postStorageimpl.add(post);
                System.out.println("Thank you, Post was added");

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data! please try again");
            addPost();
        }
    }

    private static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_POST + " for ADD_POST");
        System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please input " + POST_BY_CATEGORY + " for PRINT_POSTS_BY_CATEGORY");
        System.out.println("Please input " + ALL_POST + " for PRINT_ALL_POSTS");
    }
}
