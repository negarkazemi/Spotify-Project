package spotify;

import java.util.ArrayList;

public class Music {
    private String title;
    private User singer;
    private int numberOfStream;

    private static ArrayList<Music> allMusics = new ArrayList<>();

    public Music(String title, User singer) {
        this.title = title;
        this.singer = singer;
        this.numberOfStream = 0;
        allMusics.add(this);
    }

    public void play() {
        numberOfStream++;
        System.out.println("Playing: " + title + " by " + singer.getUsername());
    }

    public static ArrayList<Music> search(String title) {
        ArrayList<Music> foundedMusics = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.title.equalsIgnoreCase(title)) {
                foundedMusics.add(music);
            }
        }
        return foundedMusics;
    }

    // overload search
    public static Music search(String title, String singerName) {
        for (Music music : allMusics) {
            if (music.title.equalsIgnoreCase(title) && music.singer.getUsername().equalsIgnoreCase(singerName)) {
                return music;
            }
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public User getSinger() {
        return singer;
    }

    public int getNumberOfStream() {
        return numberOfStream;
    }

    public static ArrayList<Music> getAllMusics() {
        return allMusics;
    }

}
