/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import Usuario.usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metodos.hash;
import metodos.validaciones;

/**
 *
 * @author oscar
 */
public class logServlet extends HttpServlet {

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
             validaciones.usuarioArrayList();
            
            try {
                hash h = new hash();
                
             
                
                if (validaciones.validarLoginUsuario(request.getParameter("inputUser"),h.getSHA256(request.getParameter("inputPass")))  == "correcto") {
                    RequestDispatcher rd= request.getRequestDispatcher("index.html");
                rd.forward(request, response);
                    return;
                } else{
                   out.println("<body style=\"background-color: #444444;\">\n" +
                    "    <div style=\"text-align: center; color: white;\">\n" +
                    "        <h2>\n" +
                    "            Error en ingreso datos\n" +
                    "        </h2>\n" +
                    "        <P>\n" +
                    "            " +
                    "        </P>\n" +
                    "         <div>\n" +
                    "            <form action=\"loginPage.html\">\n" +
                    "                <label for=\"login\">Ir a login</label>\n" +
                    "                <div>\n" +
                    "                    <input type=\"submit\" value=\"Ir a login\">\n" +
                    "                </div>\n" +
                    "\n" +
                    "            </form>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "</body>");
              }  
                  
            } catch (Exception e) {
                out.println(e.getMessage());
                   
            }
            
            
            
            
        }
        usuarios.usuario.clear();
        
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
