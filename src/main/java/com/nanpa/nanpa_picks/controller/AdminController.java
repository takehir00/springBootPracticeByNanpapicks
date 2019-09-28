package com.nanpa.nanpa_picks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    /**
     * 管理画面TOP遷移
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("admin/top");
        return mav;
    }
}
