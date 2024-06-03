package infnet.gontijo.tp3_jessica.controller;

import infnet.gontijo.tp3_jessica.model.Aluno;
import infnet.gontijo.tp3_jessica.model.Curso;
import infnet.gontijo.tp3_jessica.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/aluno/{id}")
    public Curso addCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.salvar(id, curso);
    }

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listarComCache();
    }

    @GetMapping("/listar-sem-cache")
    public String listarSemCache() {
        long start = System.currentTimeMillis();
        List<Curso> cursos = cursoService.listarSemCache();
        long end = System.currentTimeMillis();
        long duration = end - start;
        return "Listar sem cache:" + duration + "ms";
    }

    @GetMapping("/listar-com-cache")
    public String listarComCache() {
        long start = System.currentTimeMillis();
        List<Curso> cursos = cursoService.listarComCache();
        long end = System.currentTimeMillis();
        long duration = end - start;
        return "Listar com cache:" + duration + "ms";
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id) {
        return cursoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        return cursoService.remover(id);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.atualizar(id, curso);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<?> pedidosCliente(@PathVariable Long id) {
        return cursoService.cursosPorAluno(id);
    }
}
