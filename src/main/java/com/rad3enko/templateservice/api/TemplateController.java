package com.rad3enko.templateservice.api;

import com.rad3enko.templateservice.api.dto.CreateTemplateDto;
import com.rad3enko.templateservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@RestController
@RequestMapping("templates")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TemplateController {

    private final TemplateService templateService;

    @PostMapping(value = "create")
    public void create(CreateTemplateDto createTemplateDto) {
        templateService.create(createTemplateDto.getName(),
                               createTemplateDto.getValue());
    }
}
