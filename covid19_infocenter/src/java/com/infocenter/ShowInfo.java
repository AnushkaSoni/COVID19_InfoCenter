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
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hp
 */
public class ShowInfo extends HttpServlet {

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
           
              try
            {
               // PrintWriter out = response.getWriter() ;
                        
                 Class.forName("com.mysql.jdbc.Driver") ;
                
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/covid19_help","root" , "root") ;
                
                PreparedStatement ps = con.prepareStatement("SELECT * FROM worker ") ;
                
                 ResultSet rs = ps.executeQuery() ;
                 
                 ResultSetMetaData rsmd=rs.getMetaData();
                 
                 out.print("<style>") ;
                 out.print("body{");
                 out.print("text-align:center ;");
                 out.print("position:fixed ;") ;
                 out.print("left:270px") ;
                 out.print("}");
//                 out.print(".du{") ;
//                 out.print("margin : 50% 50% ;") ;
//                 out.print("}") ;
                 out.print(".tab{") ;
                 out.print("border : 4px solid black ;") ;
                 out.print("border-collapse : collapse ;") ;
                 out.print("padding:13px ;") ;
                 out.print("font-size:19px") ;
                 out.print("text-align : center ;") ;
                 out.print("}") ;
                 out.print("</style>") ;
                 
                 out.println("<h1 class><u>Member Details</u></h1>"); 
                 out.print("<table class='du tab'>") ;
                 
                   out.print("<tr>");

                     out.print("<th class='tab'>"+rsmd.getColumnName(1)+"</th>");

                     out.print("<th class='tab'>"+rsmd.getColumnName(2)+"</th>");
                     
                     out.print("<th class='tab'>"+rsmd.getColumnName(3)+"</th>");
                     
                     out.print("<th class='tab'>"+rsmd.getColumnName(4)+"</th>");
                     
                     out.print("<th class='tab'>"+rsmd.getColumnName(5)+"</th>");
                     
                     out.print("<th class='tab'>"+rsmd.getColumnName(6)+"</th>");
                     
         
                     out.print("</tr>");
                 while(rs.next())
                 {
                    
                     
                     out.print("<tr>");      
                        out.print("<td class='tab'>"+rs.getString(1)+"</td>");

                        out.print("<td class='tab'>"+rs.getString(2)+"</td>");
                        
                        out.print("<td class='tab'>"+rs.getString(3)+"</td>");

                        out.print("<td class='tab'>"+rs.getString(4)+"</td>");
                        
                        out.print("<td class='tab'>"+rs.getString(5)+"</td>");
                        
                        out.print("<td class='tab'>"+rs.getString(6)+"</td>");
        
                     out.print("</tr>");

                     }

                     out.print("</table>");
                 }
            catch(Exception e)
            {
                e.printStackTrace() ;
            }

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
