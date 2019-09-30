package com.rad3enko.templateservice.service;

import com.rad3enko.templateservice.model.Template;

import java.util.List;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
public interface TemplateService {

    /**
     * Creates new template of provided value and name
     * @param value template text
     */
    void create(String name, String value);

    /**
     * Template with provided name
     * @param name template name
     * @return filled template
     */
    Template get(String name);

    /**
     * List of all templates
     * @return list of templates
     */
    List<Template> listTemplates();
}
