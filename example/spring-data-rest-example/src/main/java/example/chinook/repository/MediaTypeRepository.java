package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Mediatype;

public interface MediaTypeRepository extends PagingAndSortingRepository<Mediatype, Integer>{

}
