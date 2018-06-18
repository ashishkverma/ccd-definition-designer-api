package uk.gov.hmcts.ccd.definition.store.domain.validation.eventcasefield;

import org.springframework.stereotype.Component;
import uk.gov.hmcts.ccd.definition.store.domain.validation.ValidationResult;
import uk.gov.hmcts.ccd.definition.store.repository.DisplayContext;
import uk.gov.hmcts.ccd.definition.store.repository.entity.EventCaseFieldEntity;

@Component
public class EventCaseFieldOrderSummaryCaseFieldValidator implements EventCaseFieldEntityValidator {

    @Override
    public ValidationResult validate(EventCaseFieldEntity eventCaseFieldEntity,
                                     EventCaseFieldEntityValidationContext eventCaseFieldEntityValidationContext) {
        ValidationResult validationResult = new ValidationResult();

        if (isOrderSummaryType(eventCaseFieldEntity)
                && !isEmptyDisplayContext(eventCaseFieldEntity)
                && !isReadOnlyDisplayContext(eventCaseFieldEntity)) {
            validationResult.addError(
                new OrderSummaryTypeCannotBeEditableValidationError(eventCaseFieldEntity)
            );
        }

        return validationResult;
    }

    private boolean isReadOnlyDisplayContext(EventCaseFieldEntity eventCaseFieldEntity) {
        return eventCaseFieldEntity.getDisplayContext().equals(DisplayContext.READONLY);
    }

    private boolean isEmptyDisplayContext(EventCaseFieldEntity eventCaseFieldEntity) {
        return null == eventCaseFieldEntity.getDisplayContext();
    }

    private boolean isOrderSummaryType(EventCaseFieldEntity eventCaseFieldEntity) {
        return "OrderSummary".equals(eventCaseFieldEntity.getCaseField().getFieldType().getReference());
    }

}