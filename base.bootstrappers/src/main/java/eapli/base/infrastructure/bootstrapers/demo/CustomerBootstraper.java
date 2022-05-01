package eapli.base.infrastructure.bootstrapers.demo;


import eapli.base.clientusermanagement.domain.*;
import eapli.base.usermanagement.application.AddCustomerController;
import eapli.framework.actions.Action;

import java.util.ArrayList;
import java.util.List;

public class CustomerBootstraper implements Action {
    AddCustomerController customerController = new AddCustomerController();

    @Override
    public boolean execute() {
        Vat vat = Vat.valueOf("176232812");
        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("Rua dos Reis", "75", "3880-241", "Ovar", "Portugal", AddressType.SHIPMENT));
        customerController.customerBuilder("Jorge", "Sousa", vat.vatId(), "jorgesousa@gmail.com",
                "351938276432"
                , "2000-02-02", Gender.MALE, addressList);


        return false;
    }
}
