package un.lab.esa.demo.log;

import un.lab.esa.demo.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MessageSenderService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender mailSender;

    @Autowired
    public MessageSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(Message message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(message.getReceiver());
        mailMessage.setSubject(message.getSubject());
        mailMessage.setText(message.getBody());
        mailSender.send(mailMessage);
    }
}
