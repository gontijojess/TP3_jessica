package infnet.gontijo.tp3_jessica.controller;

import infnet.gontijo.tp3_jessica.model.Aluno;
import infnet.gontijo.tp3_jessica.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public Aluno criar(@RequestBody Aluno aluno) {
        return alunoService.salvar(aluno);
    }

    @GetMapping
    public List<Aluno> listar() {
        return alunoService.listar();
    }

    @GetMapping("/{id}")
    public Optional<Aluno> buscar(@PathVariable long id) {
        return alunoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable long id) {
        alunoService.remover(id);
    }

    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable long id, @RequestBody Aluno aluno) {
        return alunoService.atualizar(id, aluno);
    }

}
