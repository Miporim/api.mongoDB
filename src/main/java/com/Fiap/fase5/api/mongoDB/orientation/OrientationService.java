package com.Fiap.fase5.api.mongoDB.orientation;
import com.Fiap.fase5.api.mongoDB.audit.AuditLogService;
import com.Fiap.fase5.api.mongoDB.orientation.dto.OrientationRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
@Service public class OrientationService {
    private final OrientationRepository repository; private final AuditLogService audit;
    public OrientationService(OrientationRepository repository, AuditLogService audit) { this.repository = repository; this.audit = audit; }
    public List<Orientation> findAll() { return repository.findAll(); }
    public Orientation create(OrientationRequest r, String email) { Orientation o = repository.save(new Orientation(r.title().trim(), r.description().trim(), email)); audit.register("CREATE", "ORIENTATION", o.getId(), email); return o; }
    public Orientation update(String id, OrientationRequest r, String email) { Orientation o = find(id); o.update(r.title().trim(), r.description().trim()); Orientation saved = repository.save(o); audit.register("UPDATE", "ORIENTATION", id, email); return saved; }
    public void delete(String id, String email) { repository.delete(find(id)); audit.register("DELETE", "ORIENTATION", id, email); }
    private Orientation find(String id) { return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orientação não encontrada")); }
}
