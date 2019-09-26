package com.rad3enko.templateservice.repository;

import com.rad3enko.templateservice.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {
    boolean existsByName(String name);
    Template findByName(String name);
}
