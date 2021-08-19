package home.service;

import home.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import home.repository.ICategoryRepo;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepo iCategoryRepo;
    @Override
    public Iterable<Category> findAll() {
        return iCategoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(long id) {
        return iCategoryRepo.findById(id);
    }

    @Override
    public void save(Category category) {
        iCategoryRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        iCategoryRepo.deleteById(id);
    }
}
