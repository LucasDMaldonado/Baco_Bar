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
import Modelo.Mesa;
import Modelo.Producto;
import Modelo.Stock;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/Admin"})
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
            throws ServletException, IOException {
        processRequest(request, response);
        Usuario user = (Usuario) request.getSession().getAttribute("usr");
        GBDBaco gbd = new GBDBaco();
        String modo = request.getParameter("modo");
        String page = request.getParameter("page");

        if (user != null) {

            if (page == null || page.isEmpty()) {
                request.setAttribute("accion", "Administrar Pedidos");
                ArrayList<DTOMesaXPedido> lstMesa = gbd.obtenerMesasxPedido();
                request.setAttribute("Mesas", lstMesa);
                RequestDispatcher rd = request.getRequestDispatcher("/AdministrarPedidos.jsp");
                rd.forward(request, response);
            } else if (page.equals("TC")) {
                RequestDispatcher rd = request.getRequestDispatcher("/TerminosCondiciones.jsp");
                rd.forward(request, response);
            } 
            else if (page.equals("FAQ")) {
                RequestDispatcher rd = request.getRequestDispatcher("/FAQ.jsp");
                rd.forward(request, response);
            }else if (page.equals("TR")) {
                if (modo == null || modo.isEmpty()) {
                    request.setAttribute("accion", "Administrar Tragos");
                    ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
                    request.setAttribute("Categoria", lstCat);
                    ArrayList<Producto> lstingredientes = gbd.obtenerIngredientes();
                    request.setAttribute("ingredientes", lstingredientes);
                    ArrayList<DTOTragoxReceta> lsttragos = gbd.obtenerTragosxReceta();
                    request.setAttribute("lstTrago", lsttragos);
                    int contador = 0;
                    request.setAttribute("contador", contador);
                    RequestDispatcher rd = request.getRequestDispatcher("/AdministrarTragos.jsp");
                    rd.forward(request, response);
                } else if (modo.equals("ET")) {
                    request.setAttribute("accion", "Editar Tragos");
                    int idTrago = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
                    request.setAttribute("Categoria", lstCat);
                    ArrayList<Producto> lstingredientes = gbd.obtenerIngredientes();
                    request.setAttribute("ingredientes", lstingredientes);
                    ArrayList<DTOTragoxReceta> lstTrago = gbd.obtenerTragosxReceta();
                    for (DTOTragoxReceta dTOTxR : lstTrago) {
                        if (dTOTxR.getTrag().getId() == idTrago) {
                            int contador = 0;
                            for (Producto p : dTOTxR.getProd()) {
                                contador++;
                            }
                            DTOTragoxReceta DTOtxr = dTOTxR;
                            request.setAttribute("Edit", DTOtxr);
                            request.setAttribute("contador", contador);
                            request.setAttribute("lstTrago", lstTrago);
                            RequestDispatcher rd = request.getRequestDispatcher("/AdministrarTragos.jsp");
                            rd.forward(request, response);

                        }
                    }

                }

            } else if (page.equals("ME")) {
                if (modo == null || modo.isEmpty()) {
                    request.setAttribute("accion", "Agregar Mesas");
                    ArrayList<Mesa> lstMesas = gbd.obtenerMesas();
                    request.setAttribute("Mesas", lstMesas);
                    RequestDispatcher rd = request.getRequestDispatcher("/AdministrarMesa.jsp");
                    rd.forward(request, response);
                } else if (modo.equals("EM")) {
                    request.setAttribute("accion", "Editar Mesa");
                    int idMesa = Integer.parseInt(request.getParameter("id"));
                    ArrayList<Mesa> lstMesas = gbd.obtenerMesas();
                    request.setAttribute("Mesas", lstMesas);
                    for (Mesa mesa : lstMesas) {
                        if (mesa.getId() == idMesa) {
                            Mesa M = mesa;
                            request.setAttribute("Edit", M);
                            RequestDispatcher rd = request.getRequestDispatcher("/AdministrarMesa.jsp");
                            rd.forward(request, response);

                        }
                    }
                }
            } else if (page.equals("ST")) {
                String tbl = request.getParameter("tbl");
                int caso;
                if (modo == null || modo.isEmpty()) {
                    request.setAttribute("accion", "Agregar Producto");
                    if (tbl == null || tbl.isEmpty()) {
                        caso = 1;
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        request.setAttribute("caso", caso);
                        RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                        rd.forward(request, response);
                    } else if ("1".equals(tbl)) {
                        caso = 1;
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        request.setAttribute("caso", caso);
                        RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                        rd.forward(request, response);
                    } else if ("2".equals(tbl)) {
                        caso = 2;
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        request.setAttribute("caso", caso);
                        RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                        rd.forward(request, response);
                    } else if ("3".equals(tbl)) {
                        caso = 3;
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        request.setAttribute("caso", caso);
                        RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                        rd.forward(request, response);
                    }

                } else if (modo.equals("ES")) {
                    request.setAttribute("accion", "Editar Producto");
                    if (tbl == null || tbl.isEmpty()) {
                        caso = 1;
                        int idStock = Integer.parseInt(request.getParameter("id"));
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        for (Stock stock : lstStock) {
                            if (stock.getId() == idStock) {
                                Stock S = stock;
                                request.setAttribute("Edit", S);
                                RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                                rd.forward(request, response);

                            }
                        }
                    } else if ("1".equals(tbl)) {
                        caso = 1;
                        int idStock = Integer.parseInt(request.getParameter("id"));
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        for (Stock stock : lstStock) {
                            if (stock.getId() == idStock) {
                                Stock S = stock;
                                request.setAttribute("Edit", S);
                                RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                                rd.forward(request, response);

                            }
                        }
                    } else if ("2".equals(tbl)) {
                        caso = 2;
                        int idStock = Integer.parseInt(request.getParameter("id"));
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        for (Stock stock : lstStock) {
                            if (stock.getId() == idStock) {
                                Stock S = stock;
                                request.setAttribute("Edit", S);

                                RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                                rd.forward(request, response);

                            }
                        }
                    } else if ("3".equals(tbl)) {
                        caso = 3;
                        int idStock = Integer.parseInt(request.getParameter("id"));
                        ArrayList<Stock> lstStock = gbd.obtenerStock(caso);
                        request.setAttribute("Stock", lstStock);
                        for (Stock stock : lstStock) {
                            if (stock.getId() == idStock) {
                                Stock S = stock;
                                request.setAttribute("Edit", S);
                                RequestDispatcher rd = request.getRequestDispatcher("/AdministrarProductos.jsp");
                                rd.forward(request, response);

                            }
                        }
                    }

                }
            } else if (page.equals("MN")) {
                if (modo == null || modo.isEmpty()) {
                    int idMesa = Integer.parseInt(request.getParameter("Mesa"));
                    request.setAttribute("Mesa", idMesa);
                    ArrayList<Categoria> lstCat = gbd.obtenerCategorias();
                    request.setAttribute("Categoria", lstCat);
                    ArrayList<DTOTragoxReceta> lsttragos = gbd.obtenerTragosxReceta();
                    request.setAttribute("Tragos", lsttragos);
                    RequestDispatcher rd = request.getRequestDispatcher("/Menu.jsp");
                    rd.forward(request, response);
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
