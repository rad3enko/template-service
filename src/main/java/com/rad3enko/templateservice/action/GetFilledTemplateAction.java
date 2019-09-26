package com.rad3enko.templateservice.action;

import com.rad3enko.templateservice.action.argument.ActionArgument;
import com.rad3enko.templateservice.action.argument.GetFilledTemplateActionArgument;
import com.rad3enko.templateservice.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GetFilledTemplateAction extends BaseAction<String, GetFilledTemplateActionArgument> {

    private final TemplateService templateService;

    @Override
    protected String executeImpl(GetFilledTemplateActionArgument argument) {
        return null;
    }
}
