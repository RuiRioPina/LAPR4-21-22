/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base.usermanagement.application;

import eapli.base.clientusermanagement.domain.Address;
import eapli.base.clientusermanagement.domain.Customer;
import eapli.base.clientusermanagement.domain.CustomerBuilder;
import eapli.base.clientusermanagement.domain.Gender;
import eapli.base.clientusermanagement.repositories.CustomerRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;

import java.util.List;

/**
 * Created by nuno on 21/03/16.
 */
@UseCaseController
public class AddCustomerController {

    private final CustomerRepository repo = PersistenceContext.repositories().customers();

    public Customer customerBuilder(final String firstName, final String lastName, final String vatId, final String email, final String prefix,
                                    final String phoneNumber, final String birthday, final Gender gender,
                                    final List<Address> addresses) {
        CustomerBuilder customerBuilder = new CustomerBuilder(firstName, lastName, vatId, email, prefix, phoneNumber);
        Customer customer= (customerBuilder.withBirthday(birthday)
                .withGender(gender)
                .withAddress(addresses)
                .build());
        for (Address adr: addresses) {
            adr.setCustomer(customer);
        }
        return repo.save(customer);
    }


}
