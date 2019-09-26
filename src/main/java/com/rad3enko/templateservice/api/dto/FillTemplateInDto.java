package com.rad3enko.templateservice.api.dto;

import lombok.*;

import java.util.Map;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FillTemplateInDto {
    private Map<String, Object> keyMap;
}
