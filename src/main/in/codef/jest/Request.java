package in.codef.jest;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Request  {

    private final HttpServletRequest request;
    private String path;

    public Request(HttpServletRequest request) {
        this.request = request;
    }

    public String getPath() {
        return (this.path == null) ? this.path = this.normalizePath(request.getServletPath()) : this.path;
    }

    public String normalizePath(String path) {
        if (path == null || path.equals("")) {
            path = "/";
        } else if (path.endsWith("index.html")) {
            path = path.substring(0, path.length()-10);
        }
        return path;
    }

}
