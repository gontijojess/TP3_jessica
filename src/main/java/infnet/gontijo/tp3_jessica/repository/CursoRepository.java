package infnet.gontijo.tp3_jessica.repository;
import infnet.gontijo.tp3_jessica.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByAlunoId(Long id);
}
