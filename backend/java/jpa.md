## What Is Spring Data JPA?
1. Eliminates boilerplate code for data access
2. Provides built-in CRUD operations via interfaces like JpaRepository
3. Supports custom queries using method names or annotations
4. Integrates seamlessly with Spring Boot and Hibernate

## Steps
Define an Entity:
```
@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String name;
}
```

Create a Repository:
```
public interface UserRepository extends JpaRepository<User, Long> {
  List<User> findByName(String name);
}
```

## Why it can reduce Boilerplate Code?

Without Spring Data JPA:
```
Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :name");
query.setParameter("name", "Alice");
List<User> users = query.getResultList();
```

With Spring Data JPA:
```
List<User> users = userRepository.findByName("Alice");
```

## How to write custom query
You can define methods like:
```
List<User> findByAgeGreaterThanAndStatus(String status, int age);
```

## Use @Query Annotation
Native SQL Example:
```
@Query(value = "SELECT * FROM users WHERE status = ?1 AND age > ?2", nativeQuery = true)
List<User> findActiveUsersNative(String status, int age);
```
