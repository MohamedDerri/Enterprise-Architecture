package customers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!Test")
public class CustomerDAOMock implements ICustomerDAO{
    @Override
    public void save(Customer customer) {
        System.out.println("This is for test environment");
    }
}
