package kea.tek.tekman2.models;

public class ForeignUser extends User{
    private String host;

    public ForeignUser() {
    }

    public ForeignUser(String email, String host) {
        super(email);
        this.host = host;
    }

    public ForeignUser(int id, String email, String host) {
        super(id, email);
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
