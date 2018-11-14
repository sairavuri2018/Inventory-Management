package assesment.service;

import assesment.DAO.ExceptionExample;
import assesment.DTO.InventoryDetailsDTO;

import java.sql.SQLException;
import java.util.List;


public interface InventoryDetailsService {

    List<InventoryDetailsDTO> updateBuy(String itemname, int quantity) throws SQLException, ExceptionExample;
    List<InventoryDetailsDTO> create(String itemName, double costPrice, double sellPrice) throws SQLException, ExceptionExample;
    List<InventoryDetailsDTO> updateSell(String itemname, int quantity) throws SQLException, ExceptionExample;
    List<InventoryDetailsDTO> delete(String itemname) throws ExceptionExample;
}
