package home.repository;

import home.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepo extends CrudRepository<Comment, Integer> {
}
