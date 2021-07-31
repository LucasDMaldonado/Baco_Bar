/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GBDBaco;
import Modelo.Stock;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "Producto", urlPatterns = {"/Producto"})
public class UploadProducto extends HttpServlet {

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
         if(edit==0){
             int idstock = Integer.parseInt(request.getParameter("idStock"));
            int idprod = Integer.parseInt(request.getParameter("idProd"));            
            String producto = request.getParameter("txtProd");
            String Proveedor = request.getParameter("txtProv");
            int cantidad = Integer.parseInt(request.getParameter("txtCant"));
            boolean ingrediente = Boolean.parseBoolean(request.getParameter("chkingrediente"));
            String fecha = "2021-12-31";  
            Date date=Date.valueOf(fecha);
             Stock S ;
             S = new Stock(0, cantidad, 0, Proveedor, producto, idprod, ingrediente, date);
             gbd.agregarStock(S);
             response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=ST");
        }
        else if (edit == 1) {
             if (request.getParameter("agregar")!= null) {
                 int id = Integer.parseInt(request.getParameter("idstock")) ;
              
                 int cant = Integer.parseInt(request.getParameter("txtStock"));
                
                 gbd.addStock(id, cant);
                 response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=ST");
             }
             else if (request.getParameter("quitar")!= null) {
                 int id = Integer.parseInt(request.getParameter("idstock")) ;
               
                 int cant = Integer.parseInt(request.getParameter("txtStock"));
               
                 gbd.quitarStock(id, cant);
                 response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=ST");
             }
             else{
                int idstock = Integer.parseInt(request.getParameter("idStock"));
                int idprod = Integer.parseInt(request.getParameter("idProd"));            
                String producto = request.getParameter("txtProd");
                String Proveedor = request.getParameter("txtProv");
                int cantidad = Integer.parseInt(request.getParameter("txtCant"));
                boolean ingrediente = Boolean.parseBoolean(request.getParameter("chkingrediente"));
                String fecha = "2021-12-31";  
                Date date=Date.valueOf(fecha);
                Stock S ;
                S = new Stock(idstock, cantidad, 0, Proveedor, producto, idprod, ingrediente, date);
                gbd.agregarStock(S);
                response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=ST");
             }
            

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
