package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Playlist;

public interface PlaylistRepository extends PagingAndSortingRepository<Playlist, Integer>{

}
