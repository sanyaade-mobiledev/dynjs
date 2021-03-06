package org.dynjs.runtime.builtins.types.date.prototype;

import org.dynjs.runtime.AbstractNativeFunction;
import org.dynjs.runtime.ExecutionContext;
import org.dynjs.runtime.GlobalObject;

public class Now extends AbstractNativeFunction {

    public Now(GlobalObject globalObject, String... formalParameters) {
        super(globalObject, formalParameters);
    }

    @Override
    public Object call(ExecutionContext context, Object self, Object... args) {
        return context.getClock().currentTimeMillis();
    }
}
