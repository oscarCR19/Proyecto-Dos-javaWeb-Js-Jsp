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
public class regServlet extends HttpServlet {

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
                //objeto de tipo hash para la contraseña y validacion de user por medio de un ciclo for y arraylist
                hash h = new hash();
                //abro conexion
                Connection c = null;
                Statement stmt = null;
                ResultSet rs;
                Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ProyectoWeb",//5432
                                "postgres", "root1234");
                stmt = c.createStatement();
                
             
                
                if (validaciones.validarUsuario(request.getParameter("inputUsuario"), request.getParameter("inputEmail")) == "registrado") {
                    out.println("<body style=\"background-color: #444444;\">\n" +
                    "    <div style=\"text-align: center; color: white;\">\n" +
                    "        <h2>\n" +
                    "            Error en ingreso datos\n" +
                    "        </h2>\n" +
                    "        <P>\n" +
                    "            Ha ocurrido un error en el ingreso los datos, posiblemente el correo o usuario ya estan en uso.\n" +
                    "        </P>\n" +
                    "         <div>\n" +
                    "            <form action=\"registroPage.html\">\n" +
                    "                <label for=\"login\">Ir a registro</label>\n" +
                    "                <div>\n" +
                    "                    <input type=\"submit\" value=\"Ir a registro\">\n" +
                    "                </div>\n" +
                    "\n" +
                    "            </form>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "</body>");
                    return;
                } else if (validaciones.validarUsuario(request.getParameter("inputUsuario"), request.getParameter("inputEmail")) == "noregistrado") {
                    String sql = "INSERT INTO \"SitioWeb\".usuarios (\n"
                            + "\"nombre\", \"apellido\", email, \"usuario\", contra) VALUES (\n"
                            + "'" + request.getParameter("inputNombre") + "', '" + request.getParameter("inputApellido") + "', "
                            + "'" + request.getParameter("inputEmail") + "', '" + request.getParameter("inputUsuario") + "', "
                            + "'" + h.getSHA256(request.getParameter("inputPass")) + "');";
                    out.println("<body style=\"background-color: #444444;\">\n" +
"    <div style=\"text-align: center; color: white;\">\n" +
"        <h2>\n" +
"            Registro éxitoso\n" +
"        </h2>\n" +
"        <P>\n" +
"            Es un placer darle la bienvenida a la plataforma de ventas de vehiculos más grandes de Costa Rica.\n" +
"        </P>\n" +
"        <div>\n" +
"            <form action=\"loginPage.html\">\n" +
"                <label for=\"login\">Listo para loguearse</label>\n" +
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
                    stmt.executeUpdate(sql);
                    stmt.close();
                    c.close();
              }  
                  out.println(validaciones.validarUsuario(request.getParameter("inputUser"), request.getParameter("inputEmail")));
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
