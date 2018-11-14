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


@WebServlet(name = "updateBuyServlet", urlPatterns = "/updateBuy")
public class UpdateBuyServlet extends HttpServlet {

    public void init(ServletConfig config) {
        System.out.println("Servlet initilized");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String itemName = req.getParameter("itemName");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        PrintWriter out = res.getWriter();

        InventoryDetailsService inventory = new InventoryDetailsServiceImpl();
        try {
            List<InventoryDetailsDTO> dtoList = inventory.updateBuy(itemName, quantity);
            if (CollectionUtils.isNotEmpty(dtoList)) {
                out.write("update buy Successful");
            }
        } catch (SQLException | ExceptionExample e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
