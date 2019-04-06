package learnspringboot.core.dao;

import learnspringboot.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    Spring stereotype for ID
    Wrap database errors
 */
@Repository
// CrudRepository ??
public interface ProductRepository
        extends JpaRepository<Product, Integer> // Match @Id attribute type in Product entity
{
    // JDBC
    // SpringData -> implements the repository pattern
    // Repository pattern -> get domain object through auto generated methods
    // SpringDataJPA -> uses Hibernate to access to the DB and map the result to Java objects
    // Usage: extends Java Repository classes and SpringDataJPA implements the methods at runtime
}
