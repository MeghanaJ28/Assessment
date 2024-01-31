package com.assessment.core.models;


import java.util.List;

public interface Service {
    List<String> getPagesWithTemplate(String path, String template);
}
