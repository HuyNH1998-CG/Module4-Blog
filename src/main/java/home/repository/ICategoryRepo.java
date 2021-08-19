package home.repository;

import home.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends PagingAndSortingRepository<Category, Long> {

}
