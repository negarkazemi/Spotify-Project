package spotify;

public class PremiumBehavior implements UserBehavior{
    private int months;

    public PremiumBehavior(int months) {
        this.months = months;
    }

    @Override
    public void createPlaylist(String title, User owner) {
        Playlist playlist = new Playlist(title, owner);
        owner.getPlaylists().add(playlist);
    }

    @Override
    public void playMusic(Music music) {
        music.play();
    }

    @Override
    public void buyPremium(User owner, int months) throws InvalidOperationException{
        if (months <= 0)
            throw new InvalidOperationException("Subscription duration must be more than zero.");

        this.months += months;
        System.out.println("Your premium subscription has been extended by " + months + " months. Total remaining months: " + this.months);
    }

    public int getMonths() {
        return months;
    }

    }
