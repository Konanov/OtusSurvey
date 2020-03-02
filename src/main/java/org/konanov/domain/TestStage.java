package org.konanov.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestStage {
    private final String question;
    private final String firstAnswer;
    private final int firstQuestionPoints;
    private final String secondAnswer;
    private final int secondQuestionPoints;
    private final String thirdAnswer;
    private final int thirdQuestionPoints;
}
