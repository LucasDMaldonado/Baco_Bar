/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GBDBaco;
import Modelo.Categoria;
import Modelo.DTOMesaXPedido;
import Modelo.DTOTragoxReceta;
import Modelo.Producto;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

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
            throws ServletException, IOException 
    {
        processRequest(request, response);
        Usuario user = (Usuario) request.getSession().getAttribute("usr");
        GBDBaco gbd = new GBDBaco();
        String modo = request.getParameter("modo");
        String page = request.getParameter("page");
        if (user != null) {
            
       
        if (page == null || page.isEmpty()) {
           request.setAttribute("accion", "Administrar Mesas");
            ArrayList<DTOMesaXPedido> lstMesa = gbd.obtenerMesasxPedido();
            request.setAttribute("Mesas",lstMesa );
            RequestDispatcher rd = request.getRequestDispatcher("/AdministrarMesas.jsp");
            rd.forward(request, response);
        }
        else if(page.equals("TR"))            
        {   
            if (modo == null || modo.isEmpty()){
            request.setAttribute("accion", "Administrar Tragos");
            ArrayList<DTOTragoxReceta> lstTrago = gbd.obtenerTragosxReceta();
            request.setAttribute("Trago",lstTrago );
            ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
            request.setAttribute("Categoria",lstCat );
            ArrayList<Producto> lstingredientes = gbd.obtenerIngredientes();
            request.setAttribute("ingredientes",lstingredientes );
            RequestDispatcher rd = request.getRequestDispatcher("/AdministrarTragos.jsp");
            rd.forward(request, response);
        }
            if (modo.equals("ET")) {
                request.setAttribute("accion", "Editar Comercios");
                int idTrago = Integer.parseInt(request.getParameter("id"));
                ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
                request.setAttribute("Categoria",lstCat );
                ArrayList<DTOTragoxReceta> lstTrago = gbd.obtenerTragosxReceta();
                    for (DTOTragoxReceta dTOTxR : lstTrago) {
                        if (dTOTxR.getTrag().getId() == idTrago) {
                        DTOTragoxReceta DTOtxr = dTOTxR;
                        request.setAttribute("Edit", DTOtxr);
                        request.setAttribute("lstTrago", lstTrago);
                        RequestDispatcher rd = request.getRequestDispatcher("/AdministrarTragos.jsp");
                        rd.forward(request, response); 

                        }
                    }
            
            }
           
        }
        else if(page.equals("PR"))            
        {   
            if (modo == null || modo.isEmpty()){
            request.setAttribute("accion", "Administrar Productos");
            ArrayList<DTOTragoxReceta> lstTrago = gbd.obtenerTragosxReceta();
            request.setAttribute("Trago",lstTrago );
            ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
            request.setAttribute("Categoria",lstCat );
            ArrayList<Producto> lstingredientes = gbd.obtenerIngredientes();
            request.setAttribute("ingredientes",lstingredientes );
            RequestDispatcher rd = request.getRequestDispatcher("/AdministrarTragos.jsp");
            rd.forward(request, response);
        }
            if (modo.equals("ET")) {
                request.setAttribute("accion", "Editar Comercios");
                int idTrago = Integer.parseInt(request.getParameter("id"));
                ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
                request.setAttribute("Categoria",lstCat );
                ArrayList<DTOTragoxReceta> lstTrago = gbd.obtenerTragosxReceta();
                    for (DTOTragoxReceta dTOTxR : lstTrago) {
                        if (dTOTxR.getTrag().getId() == idTrago) {
                        DTOTragoxReceta DTOtxr = dTOTxR;
                        request.setAttribute("Edit", DTOtxr);
                        request.setAttribute("lstTrago", lstTrago);
                        RequestDispatcher rd = request.getRequestDispatcher("/AdministrarTragos.jsp");
                        rd.forward(request, response); 

                        }
                    }
            
            }
           
        }
      }
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
