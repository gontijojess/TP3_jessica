package infnet.gontijo.tp3_jessica.service;

import infnet.gontijo.tp3_jessica.exception.ResourceNotFoundException;
import infnet.gontijo.tp3_jessica.model.Aluno;
import infnet.gontijo.tp3_jessica.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    @Cacheable(value="alunos", key="#id")
    public Optional<Aluno> buscarPorId(Long id) {
        return alunoRepository.findById(id);
    }

    @Cacheable(value="alunos", key="#id")
    public void remover(Long id) {
        if(!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno com ID " + id + " não encontrado.");
        }
        alunoRepository.deleteById(id);
    }

    @CacheEvict(value="alunos", key="#id")
    public Aluno atualizar(Long id, Aluno alunoAlterado) {
        return alunoRepository.findById(id).map(aluno ->{
            aluno.setNome(alunoAlterado.getNome());
            aluno.setCpf(alunoAlterado.getCpf());
            aluno.setCursos(alunoAlterado.getCursos());
            return alunoRepository.save(aluno);
        }).orElseGet(() -> {
            alunoAlterado.setId(id);
            return alunoRepository.save(alunoAlterado);
        });
    }
}
