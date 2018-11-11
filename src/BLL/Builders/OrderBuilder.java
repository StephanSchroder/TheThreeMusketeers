/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Builders;

import BLL.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nico
 */
public class OrderBuilder {
    
    private Order order;
    private Map<Stock, Integer> stocks;
    
    public OrderBuilder(User placedByEmployee) {
        order = new Order(placedByEmployee);
        stocks = new HashMap<>();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    public void read(int orderID) {
        order = Order.read(orderID);
        List<StockOrder> stockorders = StockOrder.read(orderID);
        for (StockOrder stockorder : stockorders) {
            stocks.put(stockorder.getStock(), stockorder.getQuantity());
        }
    }
    
    public void addStock(Stock stock, int quantity) {
        stock.reduceStockCount(quantity);
        if (stocks.containsKey(stock)) {
            int previousQuantity = stocks.get(stock);
            stocks.put(stock, (previousQuantity + quantity));
        }
        else {
            stocks.put(stock, quantity);
        }
    }
    
    public void removeStock(Stock stock, int quantity) {
        stock.addStockCount(quantity);
        if (stocks.containsKey(stock)) {
            int previousQuantity = stocks.get(stock);
            if (previousQuantity <= quantity) {
                stocks.remove(stock);
            }
            else {
                stocks.put(stock, (previousQuantity - quantity));
            }
        }
    }
    
    public void publishOrder() {
        order.create();
        order.synchronise();
        Iterator it = stocks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            new StockOrder((Stock)pair.getKey(), order, (int)pair.getValue()).create();
        }
    }
    
    public void approveOrder(User approvedBy, Date receiveDate) {
        order.setStatus("Approved");
        order.setApprovedByEmployee(approvedBy);
        order.setReceiveDate(receiveDate);
        order.update();
    }
    
    public void rejectOrder(User approvedBy) {
        order.setStatus("Rejected");
        order.setApprovedByEmployee(approvedBy);
        order.update();
    }
    
    public void removeOrder() {
        Iterator it = stocks.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Stock stock = (Stock)pair.getKey();
            stock.addStockCount((int)pair.getValue());
            StockOrder.delete(order.getOrderID(), stock.getStockID());
        }
        order.delete();
    }
    
}
