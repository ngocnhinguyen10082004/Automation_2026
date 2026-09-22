package vn.edu.vtiacademy.lesson13;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashMapDemo {

  private static Logger logger = LoggerFactory.getLogger(HashMapDemo.class);

  public static void main(String[] args) {
    HashMap<String, String> user01 = new HashMap<>();
    addElementToHashMap(user01, "firstName", "Cierra");
    addElementToHashMap(user01, "lastName", "Vega");
    addElementToHashMap(user01, "age", "39");
    addElementToHashMap(user01, "email", "cierra@example.com");
    addElementToHashMap(user01, "salary", "10000");
    addElementToHashMap(user01, "department", "Insurance");
    show(user01);

    HashMap<String, String> user02 = new HashMap<>();
    addElementToHashMap(user02, "firstName", "Alden");
    addElementToHashMap(user02, "lastName", "Cantrell");
    addElementToHashMap(user02, "age", "45");
    addElementToHashMap(user02, "email", "alden@example.com");
    addElementToHashMap(user02, "salary", "12000");
    addElementToHashMap(user02, "department", "Compliance");
    show(user02);

    ArrayList<HashMap<String, String>> users = new ArrayList<>();
    users.add(user01);
    users.add(user02);
    show(users);
    logger.warn("Warning");
    logger.error("Error");
  }
  public static void addElementToHashMap(HashMap<String, String> hashMap, String key, String value) {
    try {
      logger.debug("Adding an element to HashMap");
      hashMap.put(key, value);
      logger.info("Added the element to HashMap successfully");
    } catch (Exception e) {
      logger.error("Failed to add element to HashMap. Root cause: {}", e.getMessage());  //Unable to add, Cannot to add
    }
  }

  public static void show(HashMap<String, String> hashMap) {
    for (String key: hashMap.keySet()) {
      logger.info("{}: {}", key, hashMap.get(key)); // hashMap.get(key) -> get Value of key
    }
  }

  public static void show(ArrayList<HashMap<String, String>> users) { // = read
    for (HashMap<String, String> user: users) {
      logger.info("{}", user);
    }
  }
}
