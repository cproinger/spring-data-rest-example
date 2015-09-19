package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Track;

public interface TrackRepository extends PagingAndSortingRepository<Track, Integer> {

}
