package in.codef.lateplates.controllers;

import in.codef.jest.Controller;

import java.util.Map;

public class IndexController extends Controller {

    public static long loadedAt = System.currentTimeMillis();

    public Map<String, Object> indexAction() {
        Map<String, Object> params = this.getDefaultResponseParams();
        params.put("time", loadedAt);
        return params;
    }

    public Map<String, Object> bounceAction() {

        // reset the class loader, so new code is adopted

        return this.getDefaultResponseParams();
    }

}
