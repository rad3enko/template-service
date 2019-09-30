package com.rad3enko.templateservice.api;

import com.rad3enko.templateservice.action.Action;
import com.rad3enko.templateservice.action.argument.ActionArgument;
import com.rad3enko.templateservice.action.argument.GetTemplateKeysActionArgument;
import com.rad3enko.templateservice.api.dto.CreateTemplateInDto;
import com.rad3enko.templateservice.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created on 30.09.2019.
 *
 * @author Sergey Radchenko
 */
class TemplateControllerTest {

    private final static String templateName = "template_name";
    private final static String templateValue = "template_value: ${placeholder}";

    private TemplateService service = mock(TemplateService.class);
    private Action<Set<String>> getTemplateKeysAction = mock(Action.class);
    private Action<String> getFilledTemplateAction = mock(Action.class);
    private TemplateController controller = new TemplateController(service, getTemplateKeysAction, getFilledTemplateAction);

    @Test
    void testCreate() {
        // Arrange
        CreateTemplateInDto createTemplateInDto = CreateTemplateInDto.builder()
                                                                     .name(templateName)
                                                                     .value(templateValue)
                                                                     .build();

        // Act
        controller.create(createTemplateInDto);

        // Assert
        verify(service).create(templateName, templateValue);
        verifyNoMoreInteractions(service);
    }

    @Test
    void testGetTemplateKeys() {
        // Arrange
        String[] keys = {"placeholder1", "placeholder2"};
        Set<String> keySet = new HashSet<>(Arrays.asList(keys));

        when(getTemplateKeysAction.execute(any(ActionArgument.class))).thenReturn(keySet);

        ArgumentCaptor<GetTemplateKeysActionArgument> argumentCaptor =
                ArgumentCaptor.forClass(GetTemplateKeysActionArgument.class);

        // Act
        Set<String> result = controller.getTemplateKeys(templateName);

        // Assert
        verify(getTemplateKeysAction).execute(argumentCaptor.capture());
        verifyNoMoreInteractions(getTemplateKeysAction);

        assertThat(argumentCaptor.getAllValues())
                .extracting(GetTemplateKeysActionArgument::getName)
                .containsExactly(templateName);

        assertThat(result).containsExactlyInAnyOrder(keys);
    }
}
