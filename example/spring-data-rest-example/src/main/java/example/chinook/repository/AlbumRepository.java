package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Album;

public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer>{

}
