package infnet.gontijo.tp3_jessica.service;

import infnet.gontijo.tp3_jessica.exception.ResourceNotFoundException;
import infnet.gontijo.tp3_jessica.model.Curso;
import infnet.gontijo.tp3_jessica.repository.AlunoRepository;
import infnet.gontijo.tp3_jessica.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AlunoRepository alunoRepository;

    @CachePut(value = "cursos", key = "#id")
    public Curso salvar(Long id, Curso curso) {
       return alunoRepository.findById(id).map(aluno -> {
           curso.setAluno(aluno);
           return cursoRepository.save(curso);
       }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com id:" + id));
    }

    @Cacheable(value="cursos")
    public List<Curso> listarComCache() {
        return cursoRepository.findAll();
    }

    public List<Curso> listarSemCache() {
        return cursoRepository.findAll();
    }

    @Cacheable(value = "cursos", key = "#id")
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso com ID " + id + " não encontrado."));
    }

    @CacheEvict(value = "cursos", key = "#id")
    public ResponseEntity<?> remover(Long id) {
        return cursoRepository.findById(id).map(curso -> {
            cursoRepository.delete(curso);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com o id: " + id));
    }

    public Curso atualizar(Long id, Curso cursoAtualizar) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setTitulo(cursoAtualizar.getTitulo());
            curso.setDescricao(cursoAtualizar.getDescricao());
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado com o id: " + id));
    }

    public ResponseEntity<?> cursosPorAluno(Long id){
        List<Curso> cursos = cursoRepository.findByAlunoId(id);
        if(cursos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cursos);
    }
}
