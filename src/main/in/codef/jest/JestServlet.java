package in.codef.jest;

import in.codef.jest.controller.HttpException;
import in.codef.jest.controller.InternalServerError500Exception;
import in.codef.jest.controller.NotFound404Exception;
import in.codef.lateplates.JestBootstraps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/" })
public class JestServlet extends HttpServlet {

    public String[] controllerPackages;

    @Override
    public void init() {
        // called when container is first starting the servlet
        this.controllerPackages = JestBootstraps.controllerPackages;
    }

    @Override
    protected void service(javax.servlet.http.HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException, ServletException {

        Request request = new Request(servletRequest);
        Response response = new Response(servletResponse);

        try {
            Class controllerClass;
            try {
                String controllerClassName = request.getControllerName();
                controllerClass = getControllerForPath(controllerClassName);

            } catch (ClassNotFoundException e) {
                throw new NotFound404Exception();
            }

            Controller controller;
            Map<String, Object> viewParams;
            try {
                controller = (Controller) controllerClass.getConstructors()[0].newInstance();
                viewParams = controller.dispatch(request, response);

            } catch (ReflectiveOperationException e) {
                throw new InternalServerError500Exception(null, e);
            }

            if (viewParams == null) viewParams = new HashMap<String, Object>();

            View view = new View(request, response);
            view.render("", viewParams);

        } catch (HttpException e) {
            response.sendError(e);
        }

        response.commit();

        logRequest(request, response);
    }

    protected void logRequest(Request request, Response response) {
        long requestEnd = System.nanoTime();
        double responseTime = (requestEnd - request.getRequestStart())/(10e6);
        DecimalFormat format = new DecimalFormat("#.###");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(request.getPath() + " -> " + request.getControllerName() + "." + request.getActionName());
        System.out.println("Time: " + format.format(responseTime) + "ms");
    }

    public Class getControllerForPath(String className) throws ClassNotFoundException {
        System.out.println(className);
        Class controllerClass = null;

        for (String packageName : this.controllerPackages) {
            controllerClass = Class.forName(packageName + "." + className, false, this.getClass().getClassLoader());
        }

        return controllerClass;
    }

    @Override
    public void destroy() {
        // called when container is taking down the servlet
    }
}
