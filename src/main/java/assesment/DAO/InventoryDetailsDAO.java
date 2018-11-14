package assesment.DAO;

import assesment.DTO.InventoryDetailsDTO;

import java.util.List;

public interface InventoryDetailsDAO {

    List<InventoryDetailsDTO> deleteItem(String ItemName) throws ExceptionExample;
    List<InventoryDetailsDTO> createItem(String itemName, double costPrice, double sellingPrice) throws ExceptionExample;
    List<InventoryDetailsDTO> updateBuyItem(String itemName, int quantity) throws ExceptionExample;
    List<InventoryDetailsDTO> updateSellItem(String itemName, int quantity) throws ExceptionExample;
    List<InventoryDetailsDTO>report() throws  ExceptionExample;
}
