package in.codef.jest;

import in.codef.jest.controller.HttpException;
import in.codef.jest.controller.NotFound404Exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    protected Request request;
    protected Response response;

    public Map<String, Object> dispatch(Request request, Response response) throws HttpException, ReflectiveOperationException {
        Map<String, Object> responseParams = this.getDefaultResponseParams();
        this.request = request;
        this.response = response;

        try {
            Method actionMethod = this.getClass().getDeclaredMethod(this.request.getActionName(), null);
            Map<String, Object> methodResponseParams = (Map<String, Object>)actionMethod.invoke(this, null);
            if (methodResponseParams != null) responseParams.putAll(methodResponseParams);

        } catch (NoSuchMethodException e) {
            throw new NotFound404Exception();
        }

        return responseParams;
    }

    public Map<String, Object> getDefaultResponseParams() {
        return new HashMap<String, Object>();
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Info {
        String[] urls();
    }
}
