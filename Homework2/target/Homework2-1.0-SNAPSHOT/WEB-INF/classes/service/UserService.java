package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.model.Usuari;
import deim.urv.cat.homework2.controller.UserForm;
import java.util.List;

public interface UserService {
    
    public Usuari getCustomerById(long id);
    public List<Usuari> getAllCustomers();
    public boolean modifyCustomerById(long id, Usuari nou);
    /*Dixar perque pot asta interesant lo post este*/
    public boolean addUser(UserForm user);
}
