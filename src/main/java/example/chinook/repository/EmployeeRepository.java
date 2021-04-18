package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>{

}
