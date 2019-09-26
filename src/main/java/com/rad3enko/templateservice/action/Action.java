package com.rad3enko.templateservice.action;

import com.rad3enko.templateservice.action.argument.ActionArgument;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
public interface Action<Target> {
    Target execute(ActionArgument arg);
}
