package com.example.module4_shoesshop.Utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        //Lấy url gốc
        return siteURL.replace(request.getServletPath(),"");
    }
}
