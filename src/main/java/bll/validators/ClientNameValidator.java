package bll.validators;

import model.Client;

public class ClientNameValidator implements Validator<Client> {
    @Override
    public void validate(Client client) {
        NameValidator nameValidator = new NameValidator();
        nameValidator.validate(client.getName());
    }
}