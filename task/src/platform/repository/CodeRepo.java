package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.entity.Code;

import java.util.List;

@Repository
public interface CodeRepo extends CrudRepository<Code, String> {

    default List<Code> findLatest10() {
        return findTop10ByTimeLessThanEqualAndViewsLessThanEqualOrderByDateDesc(0, 0);
    }
    List<Code> findTop10ByTimeLessThanEqualAndViewsLessThanEqualOrderByDateDesc(long time, int views);
}
