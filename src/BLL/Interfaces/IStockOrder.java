/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Interfaces;

import BLL.Order;

/**
 *
 * @author Nico
 */
public interface IStockOrder {
    public void registerStockOrder(Order order);
    public void updateStockOrder(Order order);
    public void deleteStockOrder(Order order);
}
