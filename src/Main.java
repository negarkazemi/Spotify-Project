import spotify.*;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        // Creating users
        System.out.println("\nAccount Validation----------");
        User user1 = null, user2 = null, user3 = null, user4 = null;
        try {
            user1 = new User("ali", "mypassword123");
            user2 = new User("sara_singer", "sara123456");
            user3 = new User("nil", "8434798oo");
            user4 = new User("negar", "84347");


        }catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\ncreate a user with a duplicate username-----------");
        try {
            User duplicateUser = new User("ali", "12345678");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nCreating music tracks-----------");
        Music music1 = new Music("sad", user2);
        Music music2 = new Music("mad", user2);
        Music music3 = new Music("sad", user1); // Same title, different singer
        Music music4 = new Music("bad", user3);
        for (Music music : Music.getAllMusics())
            System.out.println(music.getTitle() +"- " +music.getSinger().getUsername());

        System.out.println("\nFollowing another user-----------");
        try {
            user1.follow(user2);
            user1.follow(user2);
        }catch (InvalidOperationException e){
            System.out.println(e.getMessage());
        }
        try {
            user2.follow(user4);
        }catch (InvalidOperationException e){
            System.out.println(e.getMessage());
        }

        System.out.println("\nPlaying music as a regular user------------");
        try {
            for (int i=1; i<=6; i++)
                user1.playMusic(music1);
        }catch (InvalidOperationException e){
            System.out.println(e.getMessage());
        }

        System.out.println("\nTrying to create a playlist as a regular user-----------");
        try {
            user1.createPlaylist("My Playlist");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nBuying premium and then creating a playlist-----------");
        try {
            user1.buyPremium(2); // 2-month premium
            user1.createPlaylist("Ali's Playlist");
            System.out.println(user1.getBehavior());
        }catch (InvalidOperationException e){
            System.out.println(e.getMessage());
        }

        // Adding music to the playlist
        Playlist aliPlaylist = user1.getPlaylists().get(0);
        try {
            aliPlaylist.addMusic(music1, "mypassword123");
            aliPlaylist.addMusic(music2, "mypassword123");
            aliPlaylist.addMusic(music4, "mypassword123");
        }catch (InvalidOperationException e){
            System.out.println(e.getMessage());
        }


        System.out.println("\nTrying to add duplicate music to the playlist-----------");
        try {
            aliPlaylist.addMusic(music1, "mypassword123");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nTrying Remove music from the playlist-----------");
        try {
            aliPlaylist.removeMusic(music3, "mypassword123");
        }catch (InvalidOperationException e){
            System.out.println(e.getMessage());
        }

        System.out.println("\nTrying to edit title with incorrect password-----------");
        try {
            aliPlaylist.editTitle("New Title", "wrongpassword");
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nSearching for music by title-----------");
        System.out.println("Search for 'sad' song:");
        ArrayList<Music> sadSearched = Music.search("sad");
        for (int i=0; i<sadSearched.size(); i++)
            System.out.println(" - Found: " + sadSearched.get(i).getTitle() + " by " + sadSearched.get(i).getSinger().getUsername());

        System.out.println("\nSearching for music by title in playlist-----------");
        System.out.println("Search for 'sad' song:");
        ArrayList<Music> sadSearchedInPlayList = aliPlaylist.searchInPlaylist("sad");
        for (int i = 0; i< sadSearchedInPlayList.size(); i++)
            System.out.println(" - Found: " + sadSearchedInPlayList.get(i).getTitle() + " by " + sadSearchedInPlayList.get(i).getSinger().getUsername());


        System.out.println("\nPlaying the playlist-----------");
        aliPlaylist.playPlaylist();

        System.out.println("\nPlaying the playlist in shuffle mode-----------");
        aliPlaylist.shufflePlaylist();




    }
}