package si.kuharskimojster.api.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.kuharskimojster.api.model.ResponseModel;
import si.kuharskimojster.services.RecipeService;

@RestController
@RequestMapping("/v1")
public class RecommendationController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/health")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Health check OK", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/latest-user-recommendations")
    public ResponseEntity<ResponseModel> getLatestRecommendationsForUser(@RequestParam(name = "userId") Long userId, @RequestParam(name = "limit") int limit) {
        return new ResponseEntity<>(new ResponseModel(recipeService.recommendLatestRecipesForUser(userId, limit), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/top-rated-recommendations")
    public ResponseEntity<ResponseModel> getTopRatedRecommendations(@RequestParam(name = "limit") int limit) {
        return new ResponseEntity<>(new ResponseModel(recipeService.getTopRatedRecipes(limit), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping("/top-rated-user-recommendations")
    public ResponseEntity<ResponseModel> getTopRatedUserRecommendations(@RequestParam(name = "userId") Long userId, @RequestParam(name = "limit") int limit) {
        return new ResponseEntity<>(new ResponseModel(recipeService.recommendTopRecipesForUser(userId, limit), HttpStatus.OK.value()), HttpStatus.OK);
    }


}
