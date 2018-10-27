/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 
 */
@WebServlet(urlPatterns = {"/store"})
public class store extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static BasicAWSCredentials creds=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String bucketName = "cloudcustomers";
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             File file = new File("G:\\s3\\customers.txt"); 
          FileWriter fr = new FileWriter(file, true);
          String name=request.getParameter("name");
          String phone=request.getParameter("phone");
          String date=request.getParameter("date");
          String email=request.getParameter("email");
          String yes=request.getParameter("yes");
          String no=request.getParameter("no");
          String somewhat=request.getParameter("somewhat");
          
          
          fr.append("Name : ");
          fr.append(name);
          fr.append("\n Phone :");
          fr.append(phone);
          fr.append("\n Email :");
          fr.append(email);
          fr.append("\n Date :");
          fr.append(date);
          fr.append("\n Satsifaction :");
          if(yes!=null) fr.append(yes);
          
          if(no!=null) fr.append(no);
          
          if(somewhat!=null)fr.append(somewhat);
          fr.append("\n");
          fr.append("\n--\n");
          fr.close();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet request</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet request at " + request.getContextPath() + "</h1>");
            out.println("<p><a href=\"index.jsp\"> View Registrations or Request Help </p>");
            out.println("</body>");
            out.println("</html>");
        }
         creds = new BasicAWSCredentials("AKIAIVJP2XBT6WAKWECQ", "+3UjwsArSzHg7IBz8oadelfjkNoUdhACiTr+Qnk8");
      String path="G:\\s3\\customers.txt";
      String foldername="customers.txt";
      AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.AP_SOUTH_1).withCredentials(new AWSStaticCredentialsProvider(creds)).build();
      PutObjectResult res=s3.putObject(new PutObjectRequest(bucketName, foldername, new File(path)));
        System.out.println( res.toString());
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
