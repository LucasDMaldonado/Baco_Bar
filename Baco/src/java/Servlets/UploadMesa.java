/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Controlador.GBDBaco;
import Modelo.Mesa;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author panic
 */
@WebServlet(name = "Mesas", urlPatterns = {"/Mesas"})
public class UploadMesa extends HttpServlet {

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

        GBDBaco gbd = new GBDBaco();
        int id = Integer.parseInt(request.getParameter("idMesa"));
        int edit = Integer.parseInt(request.getParameter("Editar"));
        String Nombre = request.getParameter("txtMesa");
        String ubicacion = request.getParameter("txtUbicacion");
        Mesa M;
        if (edit == 0) {
            M = new Mesa(0, Nombre, ubicacion, 0);
            gbd.agregarMesa(M);
            ArrayList<Mesa> lstMesas = gbd.obtenerMesas();
            for (Mesa mesa : lstMesas) {
                if (Nombre.equals(mesa.getNombre())) {
                    String data = "http://localhost:8080/Baco/Admin?page=MN&Mesa=" + mesa.getId();
                    String Path = "C:\\Users\\panic\\Documents\\GitHub\\Baco_Bar\\Baco\\web\\QR\\Mesa" + Nombre + ".jpg";
                    try {
                        BitMatrix Matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 300, 300);
                        MatrixToImageWriter.writeToPath(Matrix, "jpg", Paths.get(Path));

                        response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=ME");

                    } catch (WriterException ex) {
                        Logger.getLogger(UploadMesa.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (edit == 1) {
            M = new Mesa(id, Nombre, ubicacion, 0);
            gbd.actualizarMesa(M);
            response.sendRedirect(getServletContext().getContextPath() + "/Admin?page=ME");
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
