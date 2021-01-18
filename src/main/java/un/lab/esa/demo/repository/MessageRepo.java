package un.lab.esa.demo.repository;

import un.lab.esa.demo.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message, Integer> {
}
