package com.rad3enko.templateservice.service;

import com.rad3enko.templateservice.model.Template;
import com.rad3enko.templateservice.repository.TemplateRepository;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
class TemplateServiceTest {

    private final TemplateRepository repository = mock(TemplateRepository.class);
    private final TemplateService service = new TemplateServiceImpl(repository);

    private final String templateName = "template_name";
    private final String templateValue = "value: ${placeholder}";
    private final Template mockTemplate = mock(Template.class);

    @Test
    void testCreateNotExisting() {
        // Arrange
        when(repository.existsByName(templateName)).thenReturn(false);

        ArgumentCaptor<Template> templateCaptor = ArgumentCaptor.forClass(Template.class);

        // Act
        service.create(templateName, templateValue);

        // Assert

        verify(repository).save(templateCaptor.capture());

        assertThat(templateCaptor.getValue())
                .extracting(Template::getName,
                            Template::getValue)
                .containsExactly(Tuple.tuple(templateName, templateValue).toArray());
    }

    @Test
    void testCreateExists() {
        // Arrange
        when(repository.existsByName(templateName)).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class,
                                () -> service.create(templateName, templateValue),
                                "Template with provided name already exists!");

        verify(repository).existsByName(templateName);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void testGet() {
        // Arrange
        when(repository.findByName(any(String.class))).thenReturn(mockTemplate);

        // Act
        Template result = service.get(templateName);

        // Assert
        verify(repository).findByName(templateName);
        verifyNoMoreInteractions(repository);

        assertThat(result).isSameAs(mockTemplate);
    }

    @Test
    void testListTemplates() {
        // Arrange
        Template anotherMockTemplate = mock(Template.class);

        List<Template> templates = new ArrayList<>(Arrays.asList(mockTemplate, anotherMockTemplate));

        when(repository.findAll(any(Sort.class))).thenReturn(templates);

        // Act
        List<Template> result = service.listTemplates();

        // Assert
        verify(repository).findAll(Sort.by(Sort.Direction.DESC, "name"));
        verifyNoMoreInteractions(repository);

        assertThat(result).containsExactly(mockTemplate, anotherMockTemplate);
    }
}
