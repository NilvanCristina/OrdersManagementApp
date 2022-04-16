package bll;

import bll.validators.*;
import dao.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO;
    private List<Validator<Client>> validators;

    public ClientBLL() {
        clientDAO = new ClientDAO();
        validators = new ArrayList<>();

        validators.add(new ClientNameValidator());
        validators.add(new ClientEmailValidator());
    }

    public Client findClientById(int id) {
        Client client = clientDAO.findById(id);

        if (client == null) {
            throw new NoSuchElementException("The client with id = " + id + " was not found!");
        }

        return client;
    }

    public Client findClientByName(String name) {
        Client client = clientDAO.findByName(name);

        if (client == null) {
            throw new NoSuchElementException("The client with name = " + name + " was not found!");
        }

        return client;
    }

    public Client findClientByEmail(String email) {
        Client client = clientDAO.findByEmail(email);

        if (client == null) {
            throw new NoSuchElementException("The client with email = " + email + " was not found!");
        }

        return client;
    }

    public List<Client> findAllClients() {
        List<Client> clients = clientDAO.findAll();

        if (clients == null) {
            throw new NullPointerException("There are no clients in this table!");
        }

        return clients;
    }

    private void validateClient(Client client) {
        for (Validator<Client> validator: validators)
            validator.validate(client);
    }

    public void insertClient(Client client) {
        validateClient(client);
        clientDAO.insert(client);
    }

    public void validateName(String name) {
        NameValidator nameValidator = new NameValidator();
        nameValidator.validate(name);
    }

    public void validateEmail(String email) {
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(email);
    }

    public void updateClient(String toSetColumn, String toSetValue, String conditionColumn, String conditionValue) throws Exception {
        if (toSetColumn.equals("name"))
            validateName(toSetValue);

        if (toSetColumn.equals("email"))
            validateEmail(toSetValue);

        clientDAO.update(toSetColumn, toSetValue, conditionColumn, conditionValue);
    }

    public void deleteClient(String conditionColumn, String conditionValue) {
        clientDAO.delete(conditionColumn, conditionValue);
    }
}
