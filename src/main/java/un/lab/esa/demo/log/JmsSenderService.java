package un.lab.esa.demo.log;

import un.lab.esa.demo.model.Message;
import un.lab.esa.demo.model.UpdateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsSenderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendCreateChange(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();
        String entity = clazz.getSimpleName();
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        int id = field.getInt(obj);

        Message message = new Message();
        message.setReceiver("lhtqreif.9577@gmail.com");
        message.setSubject(entity + " [create]");
        String body = "Тип изменения: create\n" +
                entity+" " + obj.toString();
        message.setBody(body);
        jmsTemplate.convertAndSend("mailbox", message);
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setType("create");
        updateInfo.setEntity(entity);
        updateInfo.setEntity_id(id);
        updateInfo.setInfo(body);
        jmsTemplate.convertAndSend("changebox", updateInfo);
    }

    public void sendUpdateChange(Object obj, Object newObj) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();
        String entity = clazz.getSimpleName();
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        int id = field.getInt(obj);

        Message message = new Message();
        message.setReceiver("lhtqreif.9577@gmail.com");
        message.setSubject(entity + " [update]");
        String body = "Тип изменения:  update\n" +
                "Старая версия: " + obj.toString() + "\n" +
                "Новая версия: " + newObj.toString();
        message.setBody(body);
        jmsTemplate.convertAndSend("mailbox", message);
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setType("update");
        updateInfo.setEntity(entity);
        updateInfo.setEntity_id(id);
        updateInfo.setInfo(body);
        jmsTemplate.convertAndSend("changebox", updateInfo);
    }

    public void sendDeleteChange(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Class clazz = obj.getClass();
        String entity = clazz.getSimpleName();
        Field field = clazz.getDeclaredField("id");
        field.setAccessible(true);
        int id = field.getInt(obj);

        Message message = new Message();
        message.setReceiver("lhtqreif.9577@gmail.com");
        message.setSubject(entity + " [delete]");
        String body = "Тип изменения: delete\n" +
                entity+" " + obj.toString();
        message.setBody(body);
        jmsTemplate.convertAndSend("mailbox", message);
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.setType("delete");
        updateInfo.setEntity(entity);
        updateInfo.setEntity_id(id);
        updateInfo.setInfo(body);
        jmsTemplate.convertAndSend("changebox", updateInfo);
    }

}
