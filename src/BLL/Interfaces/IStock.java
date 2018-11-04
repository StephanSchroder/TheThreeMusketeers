/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Interfaces;

/**
 *
 * @author Nico
 */
public interface IStock {
    public void registerStock();
    public void updateStock();
    public void deleteStock();
    public String getReportFormat();
}
