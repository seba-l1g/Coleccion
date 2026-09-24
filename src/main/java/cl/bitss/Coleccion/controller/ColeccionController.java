package cl.bitss.Coleccion.controller;
import cl.bitss.Coleccion.model.Coleccion;
import cl.bitss.Coleccion.service.ColeccionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/coleccion")
public class ColeccionController {
    private final ColeccionService service;
    public ColeccionController(ColeccionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coleccion> obtenerTodos() {
        return service.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleccion> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coleccion> crear(@Valid @RequestBody Coleccion coleccion) {
        return ResponseEntity.ok(service.crear(coleccion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coleccion> actualizar(@PathVariable Long id, @Valid @RequestBody Coleccion coleccion) {
        return service.actualizar(id, coleccion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Coleccion> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return service.obtenerPorUsuario(usuarioId);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Coleccion> obtenerPorCategoria(@PathVariable String categoria) {
        return service.obtenerPorCategoria(categoria);
    }
}
