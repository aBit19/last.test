package gr.abit.last.test.contract.store;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class StoreService {

  private static final Map<String, String> db = new ConcurrentHashMap<>();
  String store(String value) {
    String id = new Random().nextInt(0, 1_000_000) + "";
    db.put(id, value);
    return id;
  }

  Optional<String> get(String id) {
    return Optional.ofNullable(db.get(id));
  }
}
