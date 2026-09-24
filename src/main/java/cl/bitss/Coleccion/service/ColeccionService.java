package cl.bitss.Coleccion.service;
import cl.bitss.Coleccion.model.Coleccion;
import cl.bitss.Coleccion.repository.ColeccionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ColeccionService {
    private final ColeccionRepository repository;
    public ColeccionService(ColeccionRepository repository) {
        this.repository = repository;
    }

    public List<Coleccion> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Coleccion> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Coleccion crear(Coleccion coleccion) {
        return repository.save(coleccion);
    }

    public Optional<Coleccion> actualizar(Long id, Coleccion datos) {
        Optional<Coleccion> coleccionEncontrada = repository.findById(id);
        if (coleccionEncontrada.isPresent()) {
            Coleccion coleccion = coleccionEncontrada.get();
            coleccion.setUsuarioId(datos.getUsuarioId());
            coleccion.setVideojuegoId(datos.getVideojuegoId());
            coleccion.setNombreVideojuego(datos.getNombreVideojuego());
            coleccion.setCategoria(datos.getCategoria());
            return Optional.of(repository.save(coleccion));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Coleccion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Coleccion> obtenerPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }
}