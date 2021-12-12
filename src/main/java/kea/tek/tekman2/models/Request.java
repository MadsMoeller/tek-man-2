package kea.tek.tekman2.models;

public class Request {
    private int id;
    private String requestType;
    private User source;
    private User destination;

    public Request() {
    }

    public Request(String requestType, User source, User destination) {
        this.requestType = requestType;
        this.source = source;
        this.destination = destination;
    }

    public Request(int id, String requestType, User source, User destination) {
        this.id = id;
        this.requestType = requestType;
        this.source = source;
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public User getSource() {
        return source;
    }

    public void setSource(User source) {
        this.source = source;
    }

    public User getDestination() {
        return destination;
    }

    public void setDestination(User destination) {
        this.destination = destination;
    }
}
