package assesment.servlets;

import assesment.DAO.ExceptionExample;
import assesment.DTO.InventoryDetailsDTO;
import assesment.service.InventoryDetailsService;
import assesment.service.InventoryDetailsServiceImpl;
import org.apache.commons.collections4.CollectionUtils;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CreateServlet", urlPatterns = "/createPage")
public class CreateServlet extends HttpServlet {

    public void init(ServletConfig config) {
        System.out.println("Servlet initilized");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String itemName = req.getParameter("itemName");
        double costPrice = Double.valueOf(req.getParameter("costPrice"));
        double sellPrice = Double.valueOf(req.getParameter("sellingPrice"));
        PrintWriter out = res.getWriter();

        InventoryDetailsService inventory = new InventoryDetailsServiceImpl();
        try {
            List<InventoryDetailsDTO> dtoList = inventory.create(itemName, costPrice, sellPrice);
            if (CollectionUtils.isNotEmpty(dtoList)) {
                out.write("Insert Successfull");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExceptionExample exceptionExample) {
            exceptionExample.printStackTrace();
        }

    }


}
