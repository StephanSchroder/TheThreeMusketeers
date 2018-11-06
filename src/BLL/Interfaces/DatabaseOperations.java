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
public interface DatabaseOperations {
    public void create();
    public void update();
    public void delete();
    public boolean synchronise();
}
