package eapli.base.questionnaire.domain;

import eapli.base.productCategory.domain.AlphaNumericCode;
import eapli.framework.general.domain.model.Description;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {

    @Test
    void createSurvey() {
        new Survey(AlphaNumericCode.valueOf("code"), Description.valueOf("description"), Period.valueOf("2"), new Content("test"));
    }
}