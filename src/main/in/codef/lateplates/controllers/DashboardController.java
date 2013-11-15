package in.codef.lateplates.controllers;

import in.codef.jest.Controller;

import java.util.HashMap;
import java.util.Map;

public class DashboardController extends Controller {

    public Map<String, Object> indexAction() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("title", "PAGE TITLE");
        response.put("name", "Jonathan");
        return response;
    }

}
