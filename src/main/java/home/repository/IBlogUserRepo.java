package home.repository;

import home.model.BlogUser;
import org.springframework.data.repository.CrudRepository;

public interface IBlogUserRepo extends CrudRepository<BlogUser, Long> {
    BlogUser findByUsername (String username);
}
