package in.codef.jest;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Controller {

    Request request;

    public void init(Request request) {
        this.request = request;
    }

    public void tellMeMoreAbout(String path) {
        System.out.println("I'm a " + this.getClass().getName() + " and I'm being called from " + this.request.getPath());
    }

    @Target(value = ElementType.TYPE)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Info {
        String[] urls();
    }
}
