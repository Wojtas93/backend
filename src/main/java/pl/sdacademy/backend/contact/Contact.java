package pl.sdacademy.backend.contact;

import org.springframework.format.annotation.DateTimeFormat;
import pl.sdacademy.backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.ZonedDateTime;


@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotEmpty
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future
    private ZonedDateTime postDateTime = ZonedDateTime.now();
    @NotEmpty
    @Column(length = 1200)
    private String contactMessage;


    public Contact(@NotEmpty String email, @NotEmpty String contactMessage) {
        this.email = email;
        this.contactMessage = contactMessage;
    }

    public Contact() {
    }

    public Long getId() {
        return Id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ZonedDateTime getPostDateTime() {
        return postDateTime;
    }

    public void setPostDateTime(ZonedDateTime postDateTime) {
        this.postDateTime = postDateTime;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }

}
