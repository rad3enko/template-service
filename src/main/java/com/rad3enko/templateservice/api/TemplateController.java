package com.rad3enko.templateservice.api;

import com.rad3enko.templateservice.action.Action;
import com.rad3enko.templateservice.action.argument.GetTemplateKeysActionArgument;
import com.rad3enko.templateservice.api.dto.CreateTemplateDto;
import com.rad3enko.templateservice.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@RestController
@RequestMapping("templates")
public class TemplateController {

    private final TemplateService templateService;
    private final Action<Set<String>> getTemplateKeysAction;

    @Autowired
    public TemplateController(TemplateService templateService,
                              @Qualifier("getTemplateKeysAction") Action<Set<String>> getTemplateKeysAction) {
        this.templateService = templateService;
        this.getTemplateKeysAction = getTemplateKeysAction;
    }

    @PostMapping(value = "create")
    public void create(CreateTemplateDto createTemplateDto) {
        templateService.create(createTemplateDto.getName(),
                               createTemplateDto.getValue());
    }

    @GetMapping(value = "{template_name}/keys")
    public Set<String> getTemplateKeys(@PathVariable(value = "template_name") String templateName) {
        return getTemplateKeysAction.execute(GetTemplateKeysActionArgument.builder()
                                                                          .name(templateName)
                                                                          .build());
    }
}
