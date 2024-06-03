package infnet.gontijo.tp3_jessica.repository;

import infnet.gontijo.tp3_jessica.model.MaterialDidatico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialDidaticoRepository extends MongoRepository<MaterialDidatico, String> {
}
