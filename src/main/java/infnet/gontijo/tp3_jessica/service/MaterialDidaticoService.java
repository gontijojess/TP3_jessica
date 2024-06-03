package infnet.gontijo.tp3_jessica.service;

import infnet.gontijo.tp3_jessica.model.MaterialDidatico;
import infnet.gontijo.tp3_jessica.repository.MaterialDidaticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialDidaticoService {

    @Autowired
    private MaterialDidaticoRepository materialDidaticoRepository;

    public List<MaterialDidatico> buscarTodos() {
        return materialDidaticoRepository.findAll();
    }

    public MaterialDidatico salvar(MaterialDidatico materialDidatico) {
        return materialDidaticoRepository.save(materialDidatico);
    }

    public Optional<MaterialDidatico> buscarPorId(String id) {
        return materialDidaticoRepository.findById(id);
    }

    public MaterialDidatico atualizar(String id, MaterialDidatico materialDidaticoAtualizado) {
        return materialDidaticoRepository.findById(id).map(materialDidatico -> {
            materialDidatico.setTitulo(materialDidaticoAtualizado.getTitulo());
            materialDidatico.setDescricao(materialDidaticoAtualizado.getDescricao());
            materialDidatico.setAutor((materialDidaticoAtualizado.getAutor()));
            return materialDidaticoRepository.save(materialDidatico);
        }).orElseThrow(() -> new RuntimeException("Material didático não encontrado com id: " + id));
    }

    public void remover(String id) {
        if (materialDidaticoRepository.existsById(id)) {
            materialDidaticoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Material didático não encontrado com id: " + id);
        }
    }
}
