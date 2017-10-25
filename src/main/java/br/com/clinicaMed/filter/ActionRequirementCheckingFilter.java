//package br.com.clinicaMed.filter;
//
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//public class ActionRequirementCheckingFilter extends OncePerRequestFilter {
//
//    /* This matcher should not match static resources (js,css etc),
//     * url`s needed to handle the action and possibly something else,
//     * depending on your application */
//    private RequestMatcher matcher;
//
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if (session.getAttribute("principal") != null) {
//            Boolean actionRequired = false;
//        }
//    }
//
//}