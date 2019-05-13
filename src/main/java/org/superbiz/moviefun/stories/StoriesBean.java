package org.superbiz.moviefun.stories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class StoriesBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addStory(Story story) {
        entityManager.persist(story);
    }

    public Story find(long id) {
        return entityManager.find(Story.class, id);
    }

    public List<Story> getStories() {
        CriteriaQuery<Story> cq = entityManager.getCriteriaBuilder().createQuery(Story.class);
        cq.select(cq.from(Story.class));
        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    public void deleteStory(Story story) {
        entityManager.remove(story);

    }
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("DELETE FROM story").executeUpdate();
    }

    @Transactional
    public void updateStory(Story story) {
        entityManager.merge(story);
    }
}
