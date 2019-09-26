package com.rad3enko.templateservice.api.dto;

import lombok.*;

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
public class CreateTemplateDto {
    private String name;
    private String value;
}
