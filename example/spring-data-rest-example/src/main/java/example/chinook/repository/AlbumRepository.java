package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import example.chinook.domain.Album;

//@RepositoryRestResource()
public interface AlbumRepository extends PagingAndSortingRepository<Album, Integer>{

}
