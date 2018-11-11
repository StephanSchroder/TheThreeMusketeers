/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Factories;

import BLL.Interfaces.DatabaseOperations;
import BLL.Person;
import BLL.User;

/**
 *
 * @author Nico
 */
public class UserFactory implements DatabaseOperations {
    
    private Person person;
    private User user;

    public UserFactory(Person person, User user) {
        this.person = person;
        this.user = user;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void create() {
        person.create();
        person.synchronise();
        user.create();
        user.synchronise();
    }

    @Override
    public void update() {
        person.update();
        person.synchronise();
        user.update();
        user.synchronise();
    }

    @Override
    public void delete() {
        person.delete();
        user.delete();
    }

    @Override
    public boolean synchronise() {
        return ((person.synchronise() && user.synchronise())?true:false);
    }
    
}
