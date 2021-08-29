package home.service;

import home.model.Comment;
import home.repository.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentService implements ICommentService {
    @Autowired
    ICommentRepo commentRepo;
    @Override
    public Optional<Comment> findById(Integer id){
        return commentRepo.findById(id);
    }
    @Override
    public Iterable<Comment> findAll(){
        return commentRepo.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }


}
