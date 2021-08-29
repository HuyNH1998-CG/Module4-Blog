package home.service;

import home.model.Comment;
import home.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICommentService {
    Optional<Comment> findById(Integer id);

    Iterable<Comment> findAll();

    Comment save(Comment comment);

}
