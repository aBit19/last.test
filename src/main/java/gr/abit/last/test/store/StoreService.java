package gr.abit.last.test.store;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class StoreService {

  private static final Map<String, Map<String, String>> db = new ConcurrentHashMap<>();

  String store(String project, String value) {
    Map<String, String> projectTests = db.getOrDefault(project, new ConcurrentHashMap<>());
    String id = new Random().nextInt(0, 1_000_000) + "";
    projectTests.put(id, value);
    db.put(project, projectTests);
    return id;
  }

  Optional<String> get(String project, String id) {
    return Optional.ofNullable(db.getOrDefault(project, Collections.emptyMap()).get(id));
  }

  Map<String, String> get(String project) {
    return db.getOrDefault(project, Collections.emptyMap());
  }

}
