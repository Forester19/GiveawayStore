package ua.com.company.store.controller.impl;

import org.apache.log4j.Logger;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.dao.daoAbstract.AbstractDao;
import ua.com.company.store.model.entity.Image;
import ua.com.company.store.model.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;

/**
 * Created by Владислав on 03.12.2017.
 */
public class AddNewProductByAdminExecution implements CommandTypical {
    private AbstractDao productDao;
    private AbstractDao imagesDao;


    private Logger logger = Logger.getRootLogger();

    public AddNewProductByAdminExecution(AbstractDao productDao, AbstractDao imagesDao) {
        this.productDao = productDao;
        this.imagesDao = imagesDao;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] imageInformation = downloadImage(req,resp);
        String title = req.getParameter("title");
        String description= req.getParameter("description");
        String price = req.getParameter("price");

        String userID;
        HttpSession session = req.getSession(false);
        if (session !=null){
            userID =session.getAttribute("userID").toString();
        }else {
            logger.info("In servlet " + this.toString() + "session ==null");
            resp.sendRedirect("index.jsp");
            return;
        }


        Image image = new Image(0,imageInformation[0],imageInformation[1]);
        Product product = new Product(0,title,description,Integer.parseInt(price),0);
        productDao.insertImageAndProduct(image,product);
        logger.info("Admin added new product " +product.getTitle()+ " successful");
        req.setAttribute("successfulMuve","Successful added new product");
        req.getRequestDispatcher("view/AdminPage.jsp").include(req,resp);
    }
    private String[] downloadImage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String path = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\temp";
        final Part filePart = req.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream fileContent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));
            fileContent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            logger.info("File being uploaded ");

        } catch (FileNotFoundException e) {
            logger.info("Problems during file upload. Error: {0}" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
            if (fileContent != null) {
                out.close();
            }
        }

return new String[] {path,fileName};
    }

    private String getFileName(final Part part) {
         for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
