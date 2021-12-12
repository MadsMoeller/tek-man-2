package kea.tek.tekman2.models;

public class BlockedContact {
    private User user;
    private User blockedContact;

    public BlockedContact() {
    }

    public BlockedContact(User user, User blockedContact) {
        this.user = user;
        this.blockedContact = blockedContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getBlockedContact() {
        return blockedContact;
    }

    public void setBlockedContact(User blockedContact) {
        this.blockedContact = blockedContact;
    }
}
