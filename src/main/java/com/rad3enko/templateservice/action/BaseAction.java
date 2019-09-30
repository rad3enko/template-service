package com.rad3enko.templateservice.action;

import com.rad3enko.templateservice.action.argument.ActionArgument;

/**
 * Created on 26.09.2019.
 *
 * @author Sergey Radchenko
 */
public abstract class BaseAction<Target, ArgumentType extends ActionArgument> implements Action<Target> {

    @Override
    public Target execute(ActionArgument argument) {
        if (!argument.validate()) throw new RuntimeException("Action argument is not valid!");
        return executeImpl((ArgumentType) argument);
    }

    protected abstract Target executeImpl(ArgumentType argument);
}
