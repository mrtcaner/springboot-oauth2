package com.my.edu.oauth2.client.oauth2client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo510 on 28.10.2018.
 */
@Controller
public class SecuredPageController {

    @RequestMapping("/secured")
    @ResponseBody
    public String getSecuredPage() {
        return "Welcome";
    }
}
