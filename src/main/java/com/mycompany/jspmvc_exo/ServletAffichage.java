/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jspmvc_exo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class ServletAffichage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        DAO dao = new DAO(DataSourceFactory.getDataSource());
        
        String action = request.getParameter("action");
        String taux = request.getParameter("taux");
        String code = request.getParameter("code");
        String erreur = "";
        System.out.println(code + " " + taux + " " + action);
        
        if ("ADD".equals(action)) {
            if ("".equals(code) || "".equals(taux)) {
                erreur = "Veuillez remplir les deux champs";
            } else {
                try {
                    dao.AddRemise(code, Float.parseFloat(taux));
                } catch (SQLException s) {
                    dao.UpdateRemise(code.charAt(0), Float.parseFloat(taux));
                }
                
            }
        } else if ("DELETE".equals(action)) {
            try {
                dao.DeleteRemise(code);
            } catch (SQLException s) {
                erreur = "Impossible de supprimer " + code + ", ce code est utilisé.";
            }
        }
        
        List<RemiseEntity> RE = dao.AllRemise();
        request.setAttribute("erreur", erreur);
        request.setAttribute("remises", RE);
        request.getRequestDispatcher("VueMain.jsp").forward(request, response);
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
        try {
            processRequest(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServletAffichage.class
                    .getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServletAffichage.class
                    .getName()).log(Level.SEVERE, null, ex);
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

// Question pour le prof, comment faire pour récupé
