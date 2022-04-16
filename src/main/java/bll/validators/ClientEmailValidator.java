package bll.validators;

import model.Client;

public class ClientEmailValidator implements Validator<Client> {

    @Override
    public void validate(Client client) {
        EmailValidator emailValidator = new EmailValidator();
        emailValidator.validate(client.getEmail());
    }
}
