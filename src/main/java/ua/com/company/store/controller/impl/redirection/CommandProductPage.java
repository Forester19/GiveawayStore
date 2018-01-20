package ua.com.company.store.controller.impl.redirection;

import ua.com.company.store.constants.Redirection;
import ua.com.company.store.controller.command.CommandTypical;
import ua.com.company.store.model.entity.additional.ProductImage;
import ua.com.company.store.service.ProductImageService;
import ua.com.company.store.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Владислав on 20.01.2018.
 */
public class CommandProductPage implements CommandTypical {
    private ProductImageService productService;

    public CommandProductPage(ProductImageService productService) {
        this.productService = productService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  String productId = req.getParameter("id");
        ProductImage productImage = getProductByID(Integer.parseInt(productId));

        req.setAttribute("product", productImage);

        return Redirection.PRODUCT_PAGE;
    }
    private ProductImage getProductByID(int id){
      for (ProductImage productImage:productService.getAllProducts()){
          if (productImage.getId()==id){
              return productImage;
          }
      }
      return null;
    }
}
