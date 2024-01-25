package com.assessment.core.models;

import javax.annotation.Resource;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class, Resource.class },
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ArticleOverview {
    
    @ValueMapValue
    private String rootpage;

    @ValueMapValue
    private int number;

    public String getRootpage() {
        return rootpage;
    }

    public int getNumber() {
        return number;
    }

    
}
