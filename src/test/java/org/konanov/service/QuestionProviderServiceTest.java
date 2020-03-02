package org.konanov.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.konanov.domain.TestStage;
import org.konanov.mapper.StageMapper;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionProviderServiceTest {

    @Mock
    private StageMapper stageMapper;

    private QuestionProviderService service;

    @Before
    public void setUp() throws Exception {
        this.service = new QuestionProviderService(stageMapper);
    }

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
        when(stageMapper.convertToStages("/real_data.csv")).thenReturn(singletonList(stage));

        service.createQuestions("/real_data.csv");
        final TestStage actualStage = service.getStageByNumber(0);
        assertThat(actualStage).usingRecursiveComparison().isEqualTo(stage);
    }
}