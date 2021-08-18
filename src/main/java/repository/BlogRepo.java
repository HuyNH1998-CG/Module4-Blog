package repository;

import model.Blog;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
public class BlogRepo implements IBlogRepo {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Blog> findAll() {
        TypedQuery<Blog> query = entityManager.createQuery("select b from Blog b", Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findByID(Integer id) {
        TypedQuery<Blog> query = entityManager.createQuery("select b from Blog b where b.id=:id", Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(Blog blog) {
        if (blog.getId() != null) {
            entityManager.merge(blog);
        } else {
            entityManager.persist(blog);
        }
    }

    @Override
    public void remove(Integer id) {
        Blog blog = findByID(id);
        if(blog != null){
            entityManager.remove(blog);
        }
    }
}
