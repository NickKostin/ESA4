package un.lab.esa.demo.repository;

import un.lab.esa.demo.model.UpdateInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateInfoRepo extends CrudRepository<UpdateInfo, Integer> {
}