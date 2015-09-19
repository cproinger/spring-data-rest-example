package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{

}
