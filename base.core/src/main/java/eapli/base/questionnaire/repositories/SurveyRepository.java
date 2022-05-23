package eapli.base.questionnaire.repositories;

import eapli.base.productCategory.domain.AlphaNumericCode;
import eapli.base.questionnaire.domain.Survey;
import eapli.framework.domain.repositories.DomainRepository;

public interface SurveyRepository extends DomainRepository<AlphaNumericCode, Survey> {
    Iterable<Survey> findAllActive();
}
