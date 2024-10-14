<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Use the implicit session object directly (no need to declare HttpSession)
    if (session != null) {
        session.invalidate();  // Invalidate the session to log out the user
    }
    
    // Redirect to the login page or home page after logging out
    response.sendRedirect(request.getContextPath() + "/login.jsp");
%>
