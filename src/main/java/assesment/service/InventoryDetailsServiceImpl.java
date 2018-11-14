
package assesment.service;

import assesment.DAO.ExceptionExample;
import assesment.DAO.InventoryDetailsDAO;
import assesment.DAO.InventoryDetailsDAOImpl;
import assesment.DTO.InventoryDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class InventoryDetailsServiceImpl implements InventoryDetailsService {

        InventoryDetailsDAO dao = new InventoryDetailsDAOImpl();
        List<InventoryDetailsDTO> dtolist = new ArrayList<InventoryDetailsDTO>();
        String item;

        public List<InventoryDetailsDTO> updateBuy(String itemname, int quantity) throws SQLException, ExceptionExample {
            dtolist = dao.updateBuyItem(itemname,quantity);
		if (dtolist == null) {
			return null;
		}
            return dtolist;
        }

        public List<InventoryDetailsDTO> updateSell(String itemname, int quantity) throws ExceptionExample {
          dtolist = dao.updateSellItem(itemname,quantity);
            // TODO Auto-generated method stub
            if (dtolist == null) {
                return null;
            }
            return dtolist;
        }


        public List<InventoryDetailsDTO> delete(String itemname) throws ExceptionExample {
            dtolist = dao.deleteItem(itemname);
            System.out.println(dtolist);
            if (dtolist == null) {
                return null;
            }
            return dtolist;
        }

        public void report() throws ExceptionExample {
            dtolist = dao.report();

        }

       double selling_Price;
        double  cost_Price;
    @Override
    public List<InventoryDetailsDTO> create(String itemName, double costPrice, double sellPrice) throws SQLException, ExceptionExample {
        dtolist = dao.createItem(itemName,costPrice,sellPrice);
        if (dtolist == null) {
            return null;
        }
        return dtolist;

    }

    }




