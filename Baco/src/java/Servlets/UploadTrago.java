/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GBDBaco;
import Modelo.DTOTragoxReceta;
import Modelo.Producto;
import Modelo.Trago;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panic
 */
@WebServlet(name = "Trago", urlPatterns = {"/Trago"})
public class UploadTrago extends HttpServlet {

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
        
        GBDBaco gbd = new GBDBaco();
        int edit = Integer.parseInt(request.getParameter("Editar"));
        int contador = Integer.parseInt(request.getParameter("contador"));
        int categoria = Integer.parseInt(request.getParameter("cmbCategoria"));
        String Nombre = request.getParameter("txtTrago");
        double precio = Double.parseDouble(request.getParameter("txtPrecio"));
        ArrayList<Producto> ingredientes = new ArrayList<>();
        
        for (int i = 0; i < contador; i++) {
            
            if (!request.getParameter("cmbIngredientes" + (i + 1)).isEmpty());            
            {
                int idprod = Integer.parseInt(request.getParameter("cmbIngredientes" + (i + 1)));                
                Producto p = new Producto(idprod, "prod", true);
                ingredientes.add(p);
            }            
        }        
        DTOTragoxReceta NT;
        if (edit == 0) {
            Trago T = new Trago(0, Nombre, true, precio, categoria);
            NT = new DTOTragoxReceta(T, ingredientes, categoria);
            gbd.agregarTragoxReceta(NT);
            response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=TR");
        } else if (edit == 1) {
            int id = Integer.parseInt(request.getParameter("idTrago"));            
            Trago T = new Trago(id, Nombre, true, precio, categoria);
            NT = new DTOTragoxReceta(T, ingredientes, categoria);
            gbd.actualizarTragoxReceta(NT);
            response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=TR");
        }
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
