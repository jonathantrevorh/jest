package in.codef.jest;

import in.codef.jest.util.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;

public class Request  {

    private final long requestStart;

    private final HttpServletRequest request;
    private String path;
    private String actionName;
    private String controllerName;

    public Request(HttpServletRequest request) {
        this.requestStart = System.nanoTime();
        if (request == null) throw new NullPointerException();
        this.request = request;
        parseRequest();
    }

    protected void parseRequest() {
        String path = this.getPath();
        path = path.substring(1, path.length());
        boolean endsWithSlash = path.endsWith("/");

        ArrayList<String> parts = new ArrayList<String>(Arrays.asList(path.split("/")));

        actionName = (!endsWithSlash) ? parts.remove(parts.size()-1) : "index";
        if (actionName.equals("")) actionName = "index";
        if (actionName.lastIndexOf('.') >= 0) actionName = actionName.substring(0, actionName.indexOf('.'));
        actionName = Strings.lcfirst(normalizePath(actionName, true)) + "Action";

        if (parts.size() == 0) parts.add("index");

        StringBuilder controllerClassName = new StringBuilder("");

        int i;
        for (i=0 ; i < parts.size()-1 ; i++) {
            controllerClassName.append(normalizePath(parts.get(i)));
            controllerClassName.append('.');
            System.out.println(controllerClassName);
        }

        controllerClassName.append(normalizePath(parts.get(i), true) + "Controller");
        controllerName = controllerClassName.toString();
    }

    public String getPath() {
        if (path == null) {
            path = request.getServletPath().toLowerCase();
            if (path.lastIndexOf("index.html") == 0) path = "";
        }
        return path;
    }

    public String getActionName() {
        return this.actionName;
    }

    public String getControllerName() {
        return this.controllerName;
    }

    public long getRequestStart() {
        return this.requestStart;
    }

    public static String normalizePath(String nonnormalized, boolean capitalizeFirst) {
        StringBuilder path = new StringBuilder();
        nonnormalized = nonnormalized.replace("_", "");
        nonnormalized = nonnormalized.replace(" ", "");
        nonnormalized = nonnormalized.replace(".", "");

        if (nonnormalized.length() > 0) {
            String[] nonnormalizedParts = nonnormalized.split("-");
            for (int i=0 ; i < nonnormalizedParts.length ; i++) {
                path.append(capitalizeFirst ? Strings.ucfirst(nonnormalizedParts[i]) : nonnormalizedParts[i]);
            }
        }

        return path.toString();
    }

    public static String normalizePath(String nonnormalized) {
        return normalizePath(nonnormalized, false);
    }

}
