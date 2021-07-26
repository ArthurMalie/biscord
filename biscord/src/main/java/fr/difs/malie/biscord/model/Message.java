package fr.difs.malie.biscord.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;
    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    public Message(User author, String content) {
        this.content = content;
        this.author = author;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public Message() {

    }

    /**
     *  Détecte les URLs présents dans le contenu du message this.content
     * @return La liste des URLs trouvés dans this.content
     */
    public List<String> detectURLs(){
        List<String> links = new ArrayList<>();
        String regex = "\\b((?:https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|])";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            links.add(content.substring(
                    m.start(0), m.end(0)));
        }
        return links;
    }


    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

}
