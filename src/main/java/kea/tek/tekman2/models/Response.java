package kea.tek.tekman2.models;

public class Response {
    private String statusCode;
    private String phrase;

    public Response() {
    }

    public Response(String statusCode, String phrase) {
        this.statusCode = statusCode;
        this.phrase = phrase;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}
