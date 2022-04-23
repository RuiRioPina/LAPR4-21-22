/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.clientusermanagement.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class CustomerTest {

//    @Test
//    public void ensureCustomerEqualsPassesForTheSameAttributes() throws Exception {
//
//        final Customer aCustomer = new CustomerBuilder("Jorge"
//                , "Ferreira"
//                , "1201564"
//                , "1201564@isep.ipp.pt"
//                , "929197091")
//                .build();
//
//        final Customer anotherCustomer = new CustomerBuilder("Jorge"
//                , "Ferreira"
//                , "1201564"
//                , "1201564@isep.ipp.pt"
//                , "929197091")
//                .build();
//
//
//        final boolean expected = aCustomer.equals(anotherCustomer);
//
//        assertTrue(expected);
//    }
//
//    @Test
//    public void ensureCustomerEqualsFailsForDifferentAttributes() throws Exception {
//        final Customer aCustomer = new CustomerBuilder("Jorge"
//                , "Ferreira"
//                , "1201564"
//                , "1201564@isep.ipp.pt"
//                , "929197091")
//                .build();
//
//        final Customer anotherCustomer = new CustomerBuilder("Rui"
//                , "Pina"
//                , "1201568"
//                , "1201568@isep.ipp.pt"
//                , "916996827")
//                .build();
//
//        final boolean expected = aCustomer.equals(anotherCustomer);
//
//        assertFalse(expected);
//    }
//
//    @Test
//    public void ensureCustomerEqualsAreTheSameForTheSameInstance() throws Exception {
//        final Customer aCustomer = new Customer();
//
//        final boolean expected = aCustomer.equals(aCustomer);
//
//        assertTrue(expected);
//    }
//
//
//    @Test
//    public void ensureClientUserIsTheSameAsItsInstance() throws Exception {
//        final Customer aCustomer = new CustomerBuilder("Jorge"
//                , "Ferreira"
//                , "1201564"
//                , "1201564@isep.ipp.pt"
//                , "929197091")
//                .build();
//
//        final boolean expected = aCustomer.sameAs(aCustomer);
//
//        assertTrue(expected);
//    }


}
