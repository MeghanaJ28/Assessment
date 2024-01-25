package com.assessment.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/bin/createpage",
        "sling.servlet.methods=GET"
})


public class NewPage extends SlingSafeMethodsServlet {
   
    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
        try {
            ResourceResolver resourceResolver = req.getResourceResolver();
            PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

            String templatePath = "/conf/assessment/settings/wcm/templates/first--template";
            String parentPath = "/content/assessment/new-page";
            

            for (int i = 1; i <= 6; i++) {
                String pageName = "Never" + i;
                String title = "never" + i;
                
                Page sample = pageManager.create(parentPath,pageName,templatePath, title);
                resourceResolver.commit();
                res.setContentType("text/plain");
                res.getWriter().println("Page created");
            }
        } catch (Exception e) {
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}
