/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GBDBaco;
import Modelo.Mesa;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author panic
 */
@WebServlet(name = "MesaServlet", urlPatterns = {"/MesaServlet"})
public class MesaServlet extends HttpServlet {

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
        
        processRequest(request, response);
        Usuario user = (Usuario) request.getSession().getAttribute("usr");
        GBDBaco gbd = new GBDBaco();
        String modo = request.getParameter("modo");
        String page = request.getParameter("page");
        
     if(page.equals("ME"))            
        {    
            if (modo == null || modo.isEmpty()){
            request.setAttribute("accion", "Agregar Mesas");
            ArrayList<Mesa> lstMesas = gbd.obtenerMesas();
            request.setAttribute("Mesas",lstMesas );
            RequestDispatcher rd = request.getRequestDispatcher("/AdministrarMesa.jsp");            
            rd.forward(request, response);
        }
            else if (modo.equals("EM")) {
            request.setAttribute("accion", "Editar Mesa");
            int idMesa = Integer.parseInt(request.getParameter("id"));
            ArrayList<Mesa> lstMesas = gbd.obtenerMesas();
            request.setAttribute("Mesas",lstMesas );
                for (Mesa mesa : lstMesas) {
                    if (mesa.getId() == idMesa) {
                    Mesa M = mesa;
                    request.setAttribute("Edit", M);
                    RequestDispatcher rd = request.getRequestDispatcher("/AdministrarMesa.jsp");                        
                     rd.forward(request, response); 

                    }
                }   
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
