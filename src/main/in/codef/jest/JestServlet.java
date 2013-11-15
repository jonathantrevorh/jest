package in.codef.jest;

import in.codef.lateplates.Bootstraps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/" })
public class JestServlet extends HttpServlet {

    public String[] controllerPackages;

    @Override
    public void init() {
        // called when container is first starting the servlet
        this.controllerPackages = Bootstraps.controllerPackages;
    }

    @Override
    protected void service(javax.servlet.http.HttpServletRequest servletRequest, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("--------------------------------------------------------");

        Request request = new Request(servletRequest);
        Class controllerClass = getControllerForPath(request.getPath());
        Controller controller;

        try {
            controller = (Controller) controllerClass.getConstructors()[0].newInstance();
            controller.init(request);
            controller.tellMeMoreAbout(request.getPath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class getControllerForPath(String path) {
        String controllerClassName = pathToControllerClassName(path);
        System.out.println(controllerClassName);
        Class controllerClass = null;

        for (String packageName : this.controllerPackages) {
            try {
                System.out.println(packageName + "." + controllerClassName);
                controllerClass = Class.forName(packageName + "." + controllerClassName, false, this.getClass().getClassLoader());
                System.out.println("found");
            } catch (ClassNotFoundException e) {}
        }

        return controllerClass;
    }

    /*
     * / -> controllers.IndexController
     * /what -> controllers.IndexController
     * /do/ -> controllers.DoController
     * /d-o/what -> controllers.DOController
     * /do/what/mate -> controllers.do.WhatController
     */
    public String pathToControllerClassName(String path) {
        String controllerClassName = path.substring(1, path.length()).replaceAll("/", ".");
        System.out.println(controllerClassName);
        if (controllerClassName.length() == 0) {
            controllerClassName = controllerClassName.concat("Index");
        } else {
            int dotIndex = controllerClassName.lastIndexOf('.');
            controllerClassName = controllerClassName.substring(0, dotIndex);
            dotIndex = controllerClassName.lastIndexOf('.');
            controllerClassName = controllerClassName.substring(0, dotIndex)
                                + controllerClassName.substring(dotIndex, dotIndex+2).toUpperCase()
                                + controllerClassName.substring(dotIndex+2, controllerClassName.length());
        }
        controllerClassName = controllerClassName.concat("Controller");
        return controllerClassName;
    }

    @Override
    public void destroy() {
        // called when container is taking down the servlet
    }
}
