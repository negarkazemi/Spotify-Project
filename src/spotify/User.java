package spotify;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private ArrayList<User> followerList;
    private ArrayList<User> followingList;
    private UserBehavior behavior;
    private ArrayList<Playlist> playlists;

    private static ArrayList<User> allUsers = new ArrayList<>();

    public User(String userName, String password) throws InvalidOperationException{

        for (User user : allUsers)
            if(user.getUsername().equals(userName))
                throw new InvalidOperationException("Username already exists.");
        this.username = userName;

        if (password == null || password.length() < 8)
            throw new InvalidOperationException("Password must be at least 8 characters.");
        this.password = password;

        followerList = new ArrayList<>();
        followingList = new ArrayList<>();
        this.playlists = new ArrayList<>();

        this.behavior = new RegularBehavior(); // Default behavior

        allUsers.add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<User> getFollowerList() {
        return followerList;
    }

    public ArrayList<User> getFollowingList() {
        return followingList;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public UserBehavior getBehavior() {
        return behavior;
    }

    public void setBehavior(UserBehavior behavior) {
        this.behavior = behavior;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void createPlaylist (String title) throws InvalidOperationException{
        if (title == null || title.isBlank())
            throw new InvalidOperationException("Playlist title cannot be empty.");
        this.behavior.createPlaylist(title, this);
    }

    public void playMusic (Music music) throws InvalidOperationException{
        if (Music.search(music.getTitle(),music.getSinger().username)== null)
            throw new InvalidOperationException("Music not found in the library.");
        this.behavior.playMusic(music);
    }

    public void buyPremium (int month) throws InvalidOperationException{
        this.behavior.buyPremium(this, month);
    }

    public void follow(User user) throws InvalidOperationException {
        if (user ==null || !(allUsers.contains(user)))
            throw new InvalidOperationException("User does not exist.");
        if (this.followingList.contains(user))
            throw new InvalidOperationException(this.username + " are already following this user.");

        this.followingList.add(user);
        user.followerList.add(this);
        System.out.println(this.username + " followed " + user.username + ".");
    }
}
