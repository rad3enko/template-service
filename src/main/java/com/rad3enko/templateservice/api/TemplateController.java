package com.rad3enko.templateservice.api;

import com.rad3enko.templateservice.action.Action;
import com.rad3enko.templateservice.action.argument.GetFilledTemplateActionArgument;
import com.rad3enko.templateservice.action.argument.GetTemplateKeysActionArgument;
import com.rad3enko.templateservice.api.dto.CreateTemplateDto;
import com.rad3enko.templateservice.api.dto.FillTemplateInDto;
import com.rad3enko.templateservice.model.Template;
import com.rad3enko.templateservice.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

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
    private final Action<String> getFilledTemplateAction;

    @Autowired
    public TemplateController(TemplateService templateService,
                              @Qualifier("getTemplateKeysAction") Action<Set<String>> getTemplateKeysAction,
                              @Qualifier("getFilledTemplateAction") Action<String> getFilledTemplateAction) {
        this.templateService = templateService;
        this.getTemplateKeysAction = getTemplateKeysAction;
        this.getFilledTemplateAction = getFilledTemplateAction;
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

    @PostMapping(value = "{template_name}/fill")
    public String fillTemplate(@PathVariable(value = "template_name") String templateName,
                               @RequestBody FillTemplateInDto dto) {
        return getFilledTemplateAction.execute(GetFilledTemplateActionArgument.builder().name(templateName)
                                                                              .keyMap(dto.getKeyMap())
                                                                              .build());
    }

    @GetMapping(value = "list")
    public Set<String> listTemplates() {
        return templateService.listTemplates().stream()
                              .map(Template::getName)
                              .collect(Collectors.toSet());
    }

}
