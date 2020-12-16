package pl.sdacademy.backend.user;


public class TextResponse {
    private String string;

    public TextResponse(String string) {
        this.string = string;
    }

    public TextResponse() {
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
