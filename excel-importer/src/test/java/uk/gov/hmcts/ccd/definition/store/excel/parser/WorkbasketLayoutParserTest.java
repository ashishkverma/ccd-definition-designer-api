package uk.gov.hmcts.ccd.definition.store.excel.parser;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.gov.hmcts.ccd.definition.store.excel.endpoint.exception.MapperException;
import uk.gov.hmcts.ccd.definition.store.excel.parser.model.DefinitionSheet;

@DisplayName("WorkbasketLayoutParser Tests")
public class WorkbasketLayoutParserTest {

    @Mock
    private ParseContext parseContext;

    private WorkbasketLayoutParser classUnderTest;
    private Map<String, DefinitionSheet> definitionSheets;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        definitionSheets = new HashMap<>();
        classUnderTest = new WorkbasketLayoutParser(parseContext, new EntityToDefinitionDataItemRegistry());
    }

    @Test(expected = MapperException.class)
    @DisplayName("Should Fail when no worksheet provided")
    public void shouldThrowExceptionWhenWorkbasketInputWorksheetIsNotProvided() {
        classUnderTest.getDefinitionSheet(definitionSheets);
    }

    @Test
    @DisplayName("Should return name")
    public void shouldReturnNameWhenAsked() {
        assertThat(classUnderTest.getLayoutName(), is("Workbasket"));
    }
}
