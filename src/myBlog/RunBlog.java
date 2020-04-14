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
            } catch (NullPointerException e){
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
                    break;
                case ALL_POST:
                    postStorageimpl.printAllPosts();
                    break;
                default:
                    System.out.println("Warning");
            }

        }
    }

    private static void searchpost() {
        System.out.println("Write the word you want to search for");
        String search = scanner.nextLine();
        if (postStorageimpl.getPostByTitle(search).getTitle().equals(search)){

        }else postStorageimpl.searchPostsByKeyword(search);
    }

    private static void addPost() {
            System.out.println("Please input post date: title,text,category");
            String postDataStr = scanner.nextLine();
            String [] postData = postDataStr.split(",");
        Date date = new Date();
        post.setTitle(postData[0]);
        post.setText(postData[1]);
        post.setCategory(postData[2]);
        post.setCreatedData(date);
        postStorageimpl.add(post);
        System.out.println("Thank you, Post was added");
    }

    private static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_POST + " for ADD_POST");
        System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please input " + POST_BY_CATEGORY + " for PRINT_POSTS_BY_CATEGORY");
        System.out.println("Please input " + ALL_POST + " for PRINT_ALL_POSTS");
    }
    }
