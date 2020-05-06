/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infocenter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class survey extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
           
            out.println("</head>");
            out.println("<body>");
              String name = request.getParameter("name" );
            String aadhar = request.getParameter("aadhar") ;
            String contact = request.getParameter("mobile") ;
            String fever = request.getParameter("fever") ;
            String conco = request.getParameter("cc") ;
            String date = request.getParameter("date") ;
            
            
            
            try{
                
                Class.forName("com.mysql.jdbc.Driver") ;
                                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19_infocenter","root" , "root") ;
                
                PreparedStatement ps2 = con.prepareStatement("SELECT adhar FROM survey WHERE adhar=? ") ;
                ps2.setString(1 , aadhar) ;
                ResultSet rs2 = ps2.executeQuery(); 
                
                
                if(rs2.next() == true)
                {
                    out.print("copy") ;
                }
                else
                {
                   
                    PreparedStatement pstmt = con.prepareStatement("insert into covid19_infocenter.survey (name , adhar , mob_no , fever , coughncold , last_trip) values(? , ? , ? , ? , ? , ? )") ;
                    pstmt.setString(1 , name) ;
                    pstmt.setString(2 , aadhar) ;
                    pstmt.setString(3 , contact) ;
                    pstmt.setString(4 , fever) ;
                    pstmt.setString(5 , conco) ;
                    pstmt.setString(6 , date) ;
                    
                    pstmt.executeUpdate() ;
                    
                    out.print("done") ;
                   
                }
                
               
                
            }
            catch(Exception e)
            {
                out.print(e.getMessage());
                out.print("error");
            }
           
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
