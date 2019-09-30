package com.rad3enko.templateservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
@Entity(name = "template")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Template {

    @Id
    @Column(name = "name", nullable = false, updatable = false, unique = true)
    private String name;

    @Column(name = "value", nullable = false, columnDefinition = "text")
    private String value;
}
