package hu.me.fdsz.config.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@Component
public class CrosFilter implements Filter {

    private static final String HEADER_ORIGIN = "Origin";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        resp.addHeader("Access-Control-Allow-Origin", req.getHeader(HEADER_ORIGIN));
        resp.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        resp.addHeader("Access-Control-Max-Age", "-1");
        resp.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
        resp.addHeader("Authorization", "Access-Control-Allow-Headers");
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
