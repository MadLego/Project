package com.epam.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LangFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LangFilter.class);

    List<String> languages = new ArrayList<>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter starts");
        languages.add("ru");
        languages.add("en");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String language =req.getParameter("language");
        LOG.trace("Chosen language is "+language);
        String current = (String) req.getSession().getAttribute("currentLocale");
        LOG.trace("Attribute currentLocale: "+current);
        if (languages.contains(language)){
            req.getSession().setAttribute("currentLocale",language);
        }else if (current!=null){
            req.getSession().setAttribute("currentLocale",current);
        }else {
            req.getSession().setAttribute("currentLocale","en");
        }
        filterChain.doFilter(req,servletResponse);
    }
    @Override
    public void destroy() {
        LOG.debug("Filter destroy");
    }
}
