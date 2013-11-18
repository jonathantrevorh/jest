package in.codef.lateplates.controllers;

import in.codef.jest.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class DashboardController extends Controller {

    public Map<String, Object> indexAction() {
        Map<String, Object> params = this.getDefaultResponseParams();
        params.put("title", "PAGE TITLE");
        params.put("name", "Jonathan");
        return params;
    }

    public Map<String, Object> statusAction() {
        Map<String, Object> params = this.getDefaultResponseParams();
        params.put("code", "200");
        return params;
    }

    public Map<String, Object> longResponseAction() throws Exception {
        final PrintWriter writer = response.getWriter();
        Thread thoughts = new Thread() {
            public void run() {
                try {
                    response.print("1");
                    response.commit();
                    hold(1000);
                    response.print(" ... 2");
                    response.commit();
                    hold(1000);
                    response.print(" ... 3");
                    response.commit();
                    hold(1000);
                    response.print("\nNah\n");
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace(response.getWriter());
                }
            }

            private void hold(int ms) {
                try {
                    Thread.sleep(ms);
                } catch (InterruptedException e) {}
            }
        };
        thoughts.start();
        thoughts.join();
        return null;
    }

}
