package org.superbiz.moviefun.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/stories")
public class StoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final StoryBean storyBean;

    public StoryController(StoryBean storyBean) {
        this.storyBean = storyBean;
    }

    @GetMapping
    public String index(Map<String, Object> model) {
        model.put("stories", storyBean.getTopTenStories());
        return "stories";
    }
}
