package com.sessions;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName: SessionFilter
 * @Package: com.sessions
 * @Date: Nov 19, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@WebFilter(filterName = "userRoleSessionFilter", urlPatterns = {"/pages/user/*", "/pages/roles/*", "/pages/devices/*", "/pages/devicesType/*", "/pages/requestedDevices/*", "/pages/userRoles/*"})
public class UserRoleSessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc)
            throws IOException, ServletException {
        // ... tu lógica del filtro ...

        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        HttpSession session = request.getSession(false);
        System.out.println("ROLE: "+session.getAttribute("userRole"));
        if (session == null
                || session.getAttribute("userRole") == null) {
            // Redirect to the login page if userRole is not present
            response.sendRedirect(request.getContextPath() + "/index.xhtml");
        } 
        if (session.getAttribute("userRole") != null && session.getAttribute("userRole") != "admin") {
                // Redirect to the login page if userRole is not present
                response.sendRedirect(request.getContextPath() + "/pages/landing/home.xhtml");
            }
        else {
            // Continue with the chain if userRole is present
            fc.doFilter(sr, sr1);
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
