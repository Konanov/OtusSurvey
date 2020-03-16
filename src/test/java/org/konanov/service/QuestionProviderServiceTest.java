package org.konanov.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.konanov.domain.TestStage;
import org.konanov.mapper.StageMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Locale;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionProviderServiceTest {

    @Mock
    private StageMapper stageMapper;

    @InjectMocks
    private QuestionProviderService service;

    @Test
    public void service_shouldBeAbleToLoadStages() throws IOException {
        final TestStage stage = new TestStage(
                "Who is the greatest traitor in humanity history",
                "Lorgar",
                20,
                "Horus",
                -20,
                "Emperor Of Mankind",
                10);
        when(stageMapper.convertToStages(Locale.ENGLISH)).thenReturn(singletonList(stage));

        service.createQuestions(Locale.ENGLISH);
        final TestStage actualStage = service.getStageByNumber(0);
        assertThat(actualStage).usingRecursiveComparison().isEqualTo(stage);
    }
}