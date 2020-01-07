package si.kuharskimojster.repositories;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import si.kuharskimojster.entities.RecipeNode;

import java.util.Collection;

@Repository
public interface RecipeRepository extends Neo4jRepository<RecipeNode, Long> {

    @Query("MATCH (recipe:Recipe)<-[r:RATED]-() WITH  recipe.recipeId AS recipes, avg((r.rating)) AS rate RETURN recipes ORDER BY rate DESC LIMIT {limit}")
    Collection<Long> getTopRatedRecipes(@Param("limit") int limit);

    @Query("MATCH (:User {userId: {userId}})-[:SUBSCRIBES_TO]->(user:User), (user)-[p:PUBLISHED]->(recipe:Recipe)\n" +
            "WITH recipe.recipeId AS recipes, p.timestamp AS timestamp\n" +
            "RETURN recipes ORDER BY timestamp DESC LIMIT {limit}")
    Collection<Long> recommendLatestRecipesForUser(@Param("userId") Long userId, @Param("limit") int limit);

    @Query("MATCH (:User {userId: {userId}})-[:SUBSCRIBES_TO]->(user:User), (user)-[:PUBLISHED]->(recipe:Recipe)<-[r:RATED]-()\n" +
            "WITH recipe.recipeId AS recipes, avg((r.rating)) AS rate\n" +
            "RETURN recipes ORDER BY rate DESC LIMIT {limit}")
    Collection<Long> recommendTopRecipesForUser(@Param("userId") Long userId, @Param("limit") int limit);

}
