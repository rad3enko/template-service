package com.rad3enko.templateservice.action.argument;

import lombok.*;
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
@Builder
public class GetFilledTemplateActionArgument implements ActionArgument {

    private String name;
    private Map<String, Object> keyMap;

    @Override
    public boolean validate() {
        return !StringUtils.isEmpty(name.trim());
    }
}
