package si.kuharskimojster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import si.kuharskimojster.repositories.RecipeRepository;

import java.util.List;

@Service
public class RecipeService{

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public List<Long> getTopRatedRecipes(int limit){
        return (List<Long>) recipeRepository.getTopRatedRecipes(limit);
    }

    @Transactional(readOnly = true)
    public List<Long> recommendLatestRecipesForUser(Long userId, int limit){
        return (List<Long>) recipeRepository.recommendLatestRecipesForUser(userId, limit);
    }

    @Transactional(readOnly = true)
    public List<Long> recommendTopRecipesForUser(Long userId, int limit){
        return (List<Long>) recipeRepository.recommendTopRecipesForUser(userId, limit);
    }
}
