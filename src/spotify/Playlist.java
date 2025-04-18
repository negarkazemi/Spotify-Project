package spotify;

import java.util.ArrayList;
import java.util.Collections;

public class Playlist {
    private String title;
    private ArrayList<Music> playlist;
    private User owner;

    public Playlist(String title, User owner) {
        this.title = title;
        this.owner = owner;
        this.playlist = new ArrayList<>();
    }

    private void validatePassword(String password) throws InvalidOperationException {
        if (!owner.getPassword().equals(password))
            throw new InvalidOperationException("Invalid password.");

    }

    public void editTitle(String newTitle, String password) throws InvalidOperationException {
        validatePassword(password);
        setTitle(newTitle);
    }

    public void addMusic(Music music, String password) throws InvalidOperationException {
        validatePassword(password);
        if (playlist.contains(music))
            throw new InvalidOperationException("Music is already in the playlist.");
        playlist.add(music);
    }

    public void removeMusic(Music music, String password) throws InvalidOperationException {
        validatePassword(password);
        if (!playlist.contains(music))
            throw new InvalidOperationException("Music not found in playlist.");
        playlist.remove(music);
    }

    public ArrayList<Music> searchInPlaylist(String title) {
        ArrayList<Music> foundedMusics = new ArrayList<>();
        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title)) {
                foundedMusics.add(music);
            }
        }
        return foundedMusics;
    }

    // overload search in playlist
    public Music searchInPlaylist(String title, String singerName) {
        for (Music music : playlist) {
            if (music.getTitle().equalsIgnoreCase(title) && music.getSinger().getUsername().equalsIgnoreCase(singerName)) {
                return music;
            }
        }
        return null;
    }

    public void playPlaylist() {
        System.out.println("Now playing playlist: " + getTitle());
        for (Music music : playlist) {
            music.play();
        }
    }

    public void shufflePlaylist() {
        System.out.println("Shuffling and playing playlist: " + title);
        ArrayList<Music> shuffledPlayList = new ArrayList<>(playlist); //copying playList in a new list
        Collections.shuffle(shuffledPlayList); //shuffling the list
        for (Music music : shuffledPlayList) {
            music.play();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Music> getPlaylist() {
        return playlist;
    }
}
