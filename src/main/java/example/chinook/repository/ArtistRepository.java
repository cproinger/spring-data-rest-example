package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Artist;

public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer>{

}
