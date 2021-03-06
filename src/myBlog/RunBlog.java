package myBlog;

import myBlog.exception.ModelNotFoundException;
import myBlog.interfaces.Commands;
import myBlog.model.Post;
import myBlog.model.Type;
import myBlog.model.User;
import myBlog.storage.DataStorageImpl;
import java.util.Date;
import java.util.Scanner;

public class RunBlog implements Commands {

    private static final DataStorageImpl dataStorageImpl = new DataStorageImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static  User user;

    private static void main() {
        boolean isRun = true;
        while (isRun) {
            Commands.printMainCommands();
            int mainCommand;
            try {
                mainCommand = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                mainCommand = -1;
            }
            switch (mainCommand) {
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    registor();
                    break;
                case POST_BY_CATEGORY:
                    postByCategory();
                    break;
                case SEARCH_POST:
                    searchPost();
                    break;
                case ALL_POST:
                    dataStorageImpl.printAllPosts();
                    break;
                case EXIT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Wrong command!");
            }
        }
    }

    private static void registor() {
        try{
            System.out.println("Please input User data: name,surname,email,password");
            String userData = scanner.nextLine();
            String[] userDataStr =  userData.split(",");
            User userByEmail = dataStorageImpl.getUserByEmail(userDataStr[2]);
            if (userByEmail != null) {
                System.out.println("Dublicate Data!!!");
            }else {
                User user = new User();
                user.setName(userDataStr[0]);
                user.setSurname(userDataStr[1]);
                user.setEmail(userDataStr[2]);
                user.setPassword(userDataStr[3]);
                user.setType(Type.USER);
                dataStorageImpl.addUser(user);
                System.out.println("Thank you, User was added");
            }

        }catch (ArrayIndexOutOfBoundsException | ModelNotFoundException e) {
            System.out.println("Invalid Data! please try again");
            registor();
        }
    }

    private static void login() {
        System.out.println("Please input email and password(email.password)");
        String userData = scanner.nextLine();
        String[] userDataStr = userData.split(",");
        try {
            user = dataStorageImpl.getUserByEMailAndPassword(userDataStr[0],userDataStr[1]);
            if (user.getType() == Type.USER) {
                loginuser();
            }
        }catch (ModelNotFoundException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            login();
        }


    }

    private static void loginuser() {
        boolean isRun = true;
        while (isRun) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    main ();
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_ALL_POST:
                    searchPost();
                    break;
                case PRINT_ALL_POST:
                    dataStorageImpl.printAllPosts();
                    break;
            }
        }
    }

    private static void addPost() {
        try {
            System.out.println("Please input Post data: title,text,category");
            String postDataStr = scanner.nextLine();
            String[] postData = postDataStr.split(",");
            Post byTitle = dataStorageImpl.getPostByTitle(postData[0]);
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
                post.setUser(user);
                dataStorageImpl.addPost(post);
                System.out.println("Thank you, Post was added");

            }
        } catch (ArrayIndexOutOfBoundsException | ModelNotFoundException e) {
            System.out.println("Invalid Data! please try again");
            addPost();
        }


    }
    private static void searchPost() {
        System.out.println("Write the word you want to search for");
        String search = scanner.nextLine();
        try {
            dataStorageImpl.searchPostsByKeyword(search);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void postByCategory() {
        System.out.println("Please input Post category");
        try {
            String category = scanner.nextLine();
            dataStorageImpl.printPostsByCategory(category);
        }catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        main();
    }
}
