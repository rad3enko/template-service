package com.rad3enko.templateservice.action;

import com.rad3enko.templateservice.action.argument.GetFilledTemplateActionArgument;
import com.rad3enko.templateservice.model.Template;
import com.rad3enko.templateservice.service.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetFilledTemplateAction extends BaseAction<String, GetFilledTemplateActionArgument> {

    private final TemplateService templateService;
    private final Configuration configuration;

    @Override
    protected String executeImpl(GetFilledTemplateActionArgument argument) {
        final Template template = templateService.get(argument.getName());

        try {
            freemarker.template.Template freeTemplate =
                    new freemarker.template.Template(template.getName(),
                                                     template.getValue(),
                                                     configuration);
            Writer writer = new StringWriter();

            freeTemplate.process(argument.getKeyMap(), writer);

            return writer.toString();

        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать шаблон " + template.getName());
        } catch (TemplateException e) {
            throw new RuntimeException("Не удалось заполнить текст по шаблону " + template.getName());
        }
    }
}
