package com.rad3enko.templateservice.action.argument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFilledTemplateActionArgument implements ActionArgument {

    private String name;
    private Map<String, Object> keymap;

    @Override
    public boolean validate() {
        return !StringUtils.isEmpty(name.trim());
    }
}
