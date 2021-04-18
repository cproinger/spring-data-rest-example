package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Genre;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Integer>{

}
