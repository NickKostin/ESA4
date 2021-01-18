package un.lab.esa.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "message", schema = "public")
public class Message {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "receiver")
    private String receiver;
    @Column(name = "subject")
    private String subject;
    @Column(name = "body")
    private String body;

    public Message() {
    }

    public Message(String receiver, String subject, String body) {
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
