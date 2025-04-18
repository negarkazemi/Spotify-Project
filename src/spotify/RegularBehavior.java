package spotify;

public class RegularBehavior implements UserBehavior {
    private int playingLimit;

    public RegularBehavior() {
        this.playingLimit = 5;
    }

    @Override
    public void createPlaylist(String title, User owner) throws InvalidOperationException {
        throw new InvalidOperationException("Regular users are not allowed to create playlists.");
    }

    @Override
    public void playMusic(Music music) throws InvalidOperationException{
        if (playingLimit <= 0)
            throw new InvalidOperationException("Daily play limit reached. Upgrade to premium to play more music.");

        music.play();
        playingLimit--;
    }

    @Override
    public  void buyPremium (User owner, int months) throws InvalidOperationException{
        if (months <= 0)
            throw new InvalidOperationException("Subscription duration must be more than zero.");
        owner.setBehavior(new PremiumBehavior(months));
    }

    public int getPlayingLimit() {
        return playingLimit;
    }
}
