package com.example.demo.log;

import un.lab.esa.demo.model.Message;
import un.lab.esa.demo.model.UpdateInfo;
import un.lab.esa.demo.repository.MessageRepo;
import un.lab.esa.demo.repository.UpdateInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

    private final MessageSenderService messageSenderService;
    private final UpdateInfoRepo updateInfoRepo;
    private final MessageRepo messageRepo;

    @Autowired
    public JmsReceiver(MessageSenderService messageSenderService, UpdateInfoRepo updateInfoRepo, MessageRepo messageRepo) {
        this.messageSenderService = messageSenderService;
        this.updateInfoRepo = updateInfoRepo;
        this.messageRepo = messageRepo;
    }

    @JmsListener(destination = "changebox", containerFactory = "myFactory")
    public void receiveChange(UpdateInfo updateInfo) {
        updateInfoRepo.save(updateInfo);
    }

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Message message) {
        messageSenderService.send(message);
        messageRepo.save(message);
    }
}
