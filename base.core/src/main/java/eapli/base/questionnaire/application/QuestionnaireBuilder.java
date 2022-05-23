///*
// * Copyright (c) 2013-2021 the original author or authors.
// *
// * MIT License
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
// * associated documentation files (the "Software"), to deal in the Software without restriction,
// * including without limitation the rights to use, copy, modify, merge, publish, distribute,
// * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all copies or
// * substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
// * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
// */
//package eapli.base.questionnaire.application;
//
//import eapli.base.clientusermanagement.domain.*;
//import eapli.base.questionnaire.domain.*;
//import eapli.framework.domain.model.DomainFactory;
//import eapli.framework.validations.Preconditions;
//
//import java.util.List;
//
///**
// * A factory for Survey entities.
// * <p>
// * This class demonstrates the use of the factory (DDD) pattern using a fluent
// * interface. it acts as a Builder (GoF).
// *
// * @author Rui Pina 1201568@isep.ipp.pt 21/04/2022
// */
//public class QuestionnaireBuilder implements DomainFactory<Customer> {
//
//    private String surveyAlphanumericCode;
//    private String surveyDescription;
//    private String surveyPeriod;
//    private List<Section> sections;
//    private List<Question> questions;
//
//    public QuestionnaireBuilder() {
//    }
//
//    public QuestionnaireBuilder(String surveyAlphanumericCode,
//                                String surveyDescription,
//                                String surveyPeriod,
//                                List<Section> sections,
//                                List<Question> questions) {
//        Preconditions.noneNull(surveyAlphanumericCode, surveyDescription, surveyPeriod, sections, questions);
//        Preconditions.nonEmpty(sections, "Questions list can't be empty");
//        Preconditions.nonEmpty(sections, "Sections list can't be empty");
//        this.surveyAlphanumericCode = surveyAlphanumericCode;
//        this.surveyDescription = surveyDescription;
//        this.surveyPeriod = surveyPeriod;
//        this.sections = sections;
//        this.questions = questions;
//    }
//
//
//    public QuestionnaireBuilder withInstruction(final String instruction) {
//        this.instruction = instruction;
//        return this;
//    }
//
//
//    @Override
//    public Questionnaire build() {
//        // since the factory knows that all the parts are needed it could throw
//        // an exception. however, we will leave that to the constructor
//        return new Questionnaire(surveyAlphanumericCode, surveyDescription, surveyPeriod, sections);
//    }
//}
