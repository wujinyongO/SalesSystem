package org.vege;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rustbell on 5/21/17.
 */
public class MyFilter implements Filter{
    private Set<String> requireSellerAuthUrl = new HashSet<>(Arrays.asList(
            "/admin",
            "/admin.html"
    ));
    private Set<String> requireCustomerAuthUrl = new HashSet<>(Arrays.asList(
            "/user",
            "/buylogout_admin.html"
    ));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String servletPath = req.getServletPath();
        HttpSession session = req.getSession();

        // Detect Seller Auth
        // TODO seller权限
        if (requireSellerAuthUrl.contains(servletPath)
                || servletPath.startsWith("/admin/"))
        {
            String sellerid = (String) session.getAttribute("sellerid");
            System.out.println(sellerid);
            if (sellerid == null)
            {
                //服务器端跳转
//                req.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
                //客户端跳转
                ((HttpServletResponse) servletResponse).sendRedirect("/seller_login.html");
            }
            else
            {
                //已登录
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

        // Detect User Auth
        // TODO customer权限
        else if (requireCustomerAuthUrl.contains(servletPath)
                || servletPath.startsWith("/user/"))
        {
            String customerid = (String) session.getAttribute("customerid");
            if (customerid == null)
            {
                //服务器端跳转
//                req.getRequestDispatcher("/login.html").forward(servletRequest, servletResponse);
                //客户端跳转
                ((HttpServletResponse) servletResponse).sendRedirect("/userLogin.html");
            }
            else
            {
                //已登录
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

        // No need to login
        else
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
