package kea.tek.tekman2.models;

public class Friendship {
    private User user;
    private User friend;

    public Friendship() {
    }

    public Friendship(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
