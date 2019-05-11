package org.superbiz.moviefun.news;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StoryBean {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Story> getTopTenStories() {
        int id = 1;
        Story story = new Story(1, "Story " + id, "url " + id++);

        List<Story> stories = new ArrayList<Story>();
        while (id < 11) {
            stories.add(new Story(1, "Story " + id, "url " + id++));
        }
        return stories;

//        CriteriaQuery<Story> cq = entityManager.getCriteriaBuilder().createQuery(Story.class);
//        cq.select(cq.from(Story.class));
//        return entityManager.createQuery(cq).getResultList();
    }
}
