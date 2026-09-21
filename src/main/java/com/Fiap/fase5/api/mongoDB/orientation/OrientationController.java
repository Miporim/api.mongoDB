package com.Fiap.fase5.api.mongoDB.orientation;
import com.Fiap.fase5.api.mongoDB.orientation.dto.OrientationRequest;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/orientations") public class OrientationController {
    private final OrientationService service; public OrientationController(OrientationService service) { this.service = service; }
    @GetMapping public List<Orientation> findAll() { return service.findAll(); }
    @PostMapping @PreAuthorize("hasRole('LIDER')") public ResponseEntity<Orientation> create(@Valid @RequestBody OrientationRequest r, Authentication a) { Orientation o = service.create(r, a.getName()); return ResponseEntity.created(URI.create("/api/orientations/" + o.getId())).body(o); }
    @PutMapping("/{id}") @PreAuthorize("hasRole('LIDER')") public Orientation update(@PathVariable String id, @Valid @RequestBody OrientationRequest r, Authentication a) { return service.update(id, r, a.getName()); }
    @DeleteMapping("/{id}") @PreAuthorize("hasRole('LIDER')") public ResponseEntity<Void> delete(@PathVariable String id, Authentication a) { service.delete(id, a.getName()); return ResponseEntity.noContent().build(); }
}
