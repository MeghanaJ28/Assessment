package com.assessment.core.models.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.assessment.core.models.Service;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(immediate = true, service = Service.class)
public class QueryServiceImpl implements Service {

    @Reference
    private QueryBuilder queryBuilder;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public java.util.List<String> getPagesWithTemplate(String templatePath, String parentPath) {

        List<String> pagePath = new ArrayList<>();

        try (ResourceResolver resourceResolver = resolverFactory.getResourceResolver(null)) {
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

            if (pageManager != null) {
                Iterator<Page> pageIterator = pageManager.listChildren(pageManager.getPage(templatePath));

                while (pageIterator.hasNext()) {
                    Page page = pageIterator.next();

        Map<String, String> map = new HashMap<>();
        map.put("path","/content/assessment/new-page");
        map.put("type", "cq:Page");
        map.put("1_property", "jcr:content/cq:template");
        map.put("property.value", "/conf/assessment/settings/wcm/templates/first--template");

        Query query = queryBuilder.createQuery(PredicateGroup.create(map), resourceResolver.adaptTo(null));
        SearchResult result = query.getResult();
        if (!result.isEmpty()) {
            pagePath.add(page.getPath());
        }
                }
        }
    }
       catch (Exception e) {
            
        }
    }
    }

