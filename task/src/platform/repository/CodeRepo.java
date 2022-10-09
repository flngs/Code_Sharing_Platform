package platform.repository;

import org.springframework.data.repository.CrudRepository;
import platform.entity.Code;

import java.util.Collection;

public interface CodeRepo extends CrudRepository<Code, Long> {
    Collection<Code> findTop10ByOrderByIdDesc();
}
