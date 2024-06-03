package infnet.gontijo.tp3_jessica.repository;
import infnet.gontijo.tp3_jessica.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
