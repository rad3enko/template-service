package com.rad3enko.templateservice.action;

import com.rad3enko.templateservice.action.argument.GetTemplateKeysActionArgument;
import com.rad3enko.templateservice.model.Template;
import com.rad3enko.templateservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetTemplateKeysAction extends BaseAction<Set<String>, GetTemplateKeysActionArgument> {

    private final TemplateService templateService;

    @Value("${freemarker.placeholder.regex}")
    private String regex;

    @Override
    protected Set<String> executeImpl(GetTemplateKeysActionArgument argument) {
        final Template template = templateService.get(argument.getName());

        final Matcher matcher = Pattern.compile(regex).matcher(template.getValue());

        Set<String> keys = new HashSet<>();

        while (matcher.find()) keys.add(matcher.group(0)
                                               .replaceAll("[\\$\\{\\}]", ""));

        return keys;
    }
}
