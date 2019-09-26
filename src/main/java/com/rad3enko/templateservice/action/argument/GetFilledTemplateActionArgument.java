package com.rad3enko.templateservice.action.argument;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class GetFilledTemplateActionArgument {
    private String name;
    private Map<String, Object> keymap;
}
