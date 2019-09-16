package com.nanpa.nanpa_picks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminArticleController {

    /**
     * 記事管理画面TOP
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("admin/articles/top");
        return mav;
    }

    /**
     * 記事登録画面
     *
     * @return
     */
    @RequestMapping(value = "/admin/article/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(ModelAndView mav) {
        mav.setViewName("admin/articles/registerForm");
        return mav;
    }

    /**
     * 記事登録
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST)
    public ModelAndView register(ModelAndView mav) {
        return mav;
    }
}
