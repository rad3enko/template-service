package com.rad3enko.templateservice.action.argument;

import lombok.*;
import org.springframework.util.StringUtils;

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
public class GetTemplateKeysActionArgument implements ActionArgument {

    private String name;

    @Override
    public boolean validate() {
        return !StringUtils.isEmpty(name.trim());
    }
}
