package gr.abit.anbtest.store;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class StoreService {

  private static Map<String, Map<String, String>> db = new ConcurrentHashMap<>();
  String store(String type, String value) {
    String id = new Random().nextInt(0, 1_000_000) + "";
    if (! db.containsKey(type)) {
      db.put(type, new ConcurrentHashMap<>());
    }
    db.get(type).put(id, value);
    return id;
  }

  Optional<String> get(String type, String id) {
    Map<String, String> typeDb = db.get(type);
    if (typeDb == null) {
      return Optional.empty();
    } else {
      return Optional.ofNullable(typeDb.get(id));
    }
  }
}
