package assesment.DAO;

import assesment.DTO.InventoryDetailsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("deprecation")
public class InventoryDetailsDAOImpl implements InventoryDetailsDAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/inventory?allowPublicKeyRetrieval=true&useSSL=false";

    //  Database credentials
    static final String USER = "root";
    static final String PASSWORD = "qwerty123";

    Connection conn = null;
    Statement stmt = null;


    @Override
    public List<InventoryDetailsDTO> deleteItem(String itemName) throws ExceptionExample {

        List <InventoryDetailsDTO> dtoList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        try{

            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = " Delete from inventory.inventory_management where productName ='"+ itemName +"';";
            System.out.println(sql);
            stmt.execute(sql);


            //STEP 5: Extract data from result set
            /*while(rs.next()){
                //Retrieve by column name
                String itemName = rs.getString("productName");
                InventoryDetailsDTO inventoryDetailsDTO = new InventoryDetailsDTO(itemName);
                dtoList.add(inventoryDetailsDTO);

                //Display values
                System.out.print("product Name : " + itemName);

            }*/


            //STEP 6: Clean-up environment
            //rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
                throw new ExceptionExample("item not listed");
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return dtoList;

    }
//end deleteItem

    @Override
    public List<InventoryDetailsDTO> createItem(String itemName, double costPrice, double sellingPrice) throws ExceptionExample {
        List<InventoryDetailsDTO> dtoList = new ArrayList<>();

        try {

            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            String productId = "SELECT max(productId) FROM inventory.inventory_management";
            ResultSet maxProductId = stmt.executeQuery(productId);
            int productId_max = 0;
            while (maxProductId.next()) {

                productId_max = maxProductId.getInt(1) + 1;
            }

            sql = "INSERT INTO inventory_management (productId, productName, costPrice, sellingPrice) values ("
                    + productId_max + ",'"  + itemName + "'," + costPrice + "," + sellingPrice + ");";
            System.out.println(sql);
            boolean rs = stmt.execute(sql);


            //STEP 5: Extract data from result set
            /*while (rs.next()) {
                //Retrieve by column name
                String itemName_db = rs.getString("ProductName");
                float sellingPrice_db = rs.getFloat("sellingPrice");
                float costPrice_db = rs.getFloat("costPrice");
                InventoryDetailsDTO inventoryDetailsDTO = new InventoryDetailsDTO(itemName_db, costPrice_db, sellingPrice_db);
                dtoList.add(inventoryDetailsDTO);

                //Display values
                System.out.print(" product Id: " + productId_max);
                System.out.print(" product Name: " + itemName_db);
                System.out.print(", cost Price: " + costPrice_db);
                System.out.println(", selling Price: " + sellingPrice_db);
            }*/


            //STEP 6: Clean-up environment
            //1rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ExceptionExample("rows could not be updated ");
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return dtoList;

    }
    //end create item

    @Override
    public List<InventoryDetailsDTO> updateSellItem(String itemName, int quantity) throws ExceptionExample {
        List<InventoryDetailsDTO> dtoList = new ArrayList<>();

        try {

            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String itemQuantity = "SELECT quantity FROM inventory.inventory_management where 'productName' = '"+itemName+"'";
            ResultSet item_Quantity = stmt.executeQuery(itemQuantity);
            int item_final = 0;
            while (item_Quantity.next()) {

                item_final = item_Quantity.getInt(1) - quantity;
            }


            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql_sell;

            sql_sell =  "UPDATE inventory.inventory_management SET 'quantity' = " + item_final
                    + "  WHERE ('productName' = " + itemName + ")";
            ResultSet rs = stmt.executeQuery(sql_sell);


            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int quantity_db_sell = rs.getInt("quantity");
                String itemName_db_sell = rs.getString("ProductName");

                InventoryDetailsDTO inventoryDetailsDTO = new InventoryDetailsDTO(itemName_db_sell, quantity_db_sell);
                dtoList.add(inventoryDetailsDTO);

                //Display values
                System.out.print(" product Name: " + itemName_db_sell);
                System.out.print(", quantity: " + quantity_db_sell);
            }


            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ExceptionExample("quantity could not be reduced ");
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return dtoList;
    }

    @Override
    public List<InventoryDetailsDTO> report() throws ExceptionExample {

        List <InventoryDetailsDTO> dtoList = new ArrayList<>();

        try{

            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM inventory.inventory_management order by productName asc;";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);


            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                int productId  = rs.getInt("productId");
                String productName = rs.getString("ProductName");
                double costPrice = rs.getFloat("costPrice");
                double sellingPrice = rs.getFloat("sellingPrice");
                InventoryDetailsDTO inventoryDetailsDTO = new InventoryDetailsDTO();
                dtoList.add(inventoryDetailsDTO);

                //Display values
                System.out.print("product Id: \n " + productId);
                System.out.print("\n product Name: \n " + productName);
                System.out.print("\n cost Price: \n " + costPrice);
                System.out.println("\n selling price : \n " + sellingPrice);
                System.out.println("----------------------------------");
            }


            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
                throw new ExceptionExample("please check your connection");
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return dtoList;

    }


    @Override
    public List<InventoryDetailsDTO> updateBuyItem(String itemName, int quantity) throws ExceptionExample {

        List<InventoryDetailsDTO> dtoList = new ArrayList<>();
        int adj_quantity;

        try {

            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;

            String itemQuantity = "SELECT quantity FROM inventory.inventory_management where productName = '"+itemName+"'";
            ResultSet item_Quantity = stmt.executeQuery(itemQuantity);
            int item_final = 0;
            while (item_Quantity.next()) {

                item_final = item_Quantity.getInt(1) + quantity;
            }

            sql =  "UPDATE inventory.inventory_management SET quantity = '"+item_final+"' WHERE productName = '"+itemName+"'";
            stmt.execute(sql);


            //STEP 5: Extract data from result set
           /* while (rs.next()) {
                //Retrieve by column name
                String itemName_db = rs.getString("ProductName");
                int quantity_db = rs.getInt("quantity");
                InventoryDetailsDTO inventoryDetailsDTO = new InventoryDetailsDTO(itemName_db, quantity_db);
                dtoList.add(inventoryDetailsDTO);

                //Display values
                System.out.print(" product Name: " + itemName_db);
                System.out.print(", quantity: " + quantity_db);
            }*/


            //STEP 6: Clean-up environment
            //rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                throw new ExceptionExample("quantity could not be updated ");
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
        return dtoList;
    }
}
