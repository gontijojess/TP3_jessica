package infnet.gontijo.tp3_jessica.controller;

import infnet.gontijo.tp3_jessica.model.MaterialDidatico;
import infnet.gontijo.tp3_jessica.service.MaterialDidaticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materiaisdidaticos")
public class MaterialDidaticoController {

    @Autowired
    private MaterialDidaticoService materialDidaticoService;

    @PostMapping
    public MaterialDidatico save(@RequestBody MaterialDidatico materialDidatico){
        return materialDidaticoService.salvar(materialDidatico);
    }

    @GetMapping
    public List<MaterialDidatico> listar(){
        return materialDidaticoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDidatico> buscarPorId(@PathVariable String id) {
        Optional<MaterialDidatico> materialDidatico = materialDidaticoService.buscarPorId(id);
        return materialDidatico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDidatico> atualizar(@PathVariable String id, @RequestBody MaterialDidatico materialDidatico) {
        try {
            MaterialDidatico atualizado = materialDidaticoService.atualizar(id, materialDidatico);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        try {
            materialDidaticoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
