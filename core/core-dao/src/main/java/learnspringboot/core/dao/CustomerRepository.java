package learnspringboot.core.dao;

import learnspringboot.core.domain.Customer;
import learnspringboot.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Integer>
{

}
