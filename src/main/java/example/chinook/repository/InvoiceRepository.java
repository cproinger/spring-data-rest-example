package example.chinook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import example.chinook.domain.Invoice;

public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Integer>{

}
