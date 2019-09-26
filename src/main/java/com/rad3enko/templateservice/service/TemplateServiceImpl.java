package com.rad3enko.templateservice.service;

import com.rad3enko.templateservice.model.Template;
import com.rad3enko.templateservice.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository repository;

    @Override
    @Transactional
    public void create(String name, String value) {
        if (!repository.existsByName(name)) {
            repository.save(Template.builder()
                                    .name(name)
                                    .value(value)
                                    .build());
        } else {
            throw new RuntimeException("Template with provided name already exists!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Template get(String name) {
        return repository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Template> listTemplates() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }
}
