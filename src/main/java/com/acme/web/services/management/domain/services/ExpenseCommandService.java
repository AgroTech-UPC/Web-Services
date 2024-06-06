package com.acme.web.services.management.domain.services;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.commands.DeleteExpenseCommand;
import com.acme.web.services.management.domain.model.commands.UpdateExpenseCommand;

import java.util.Optional;

/**
 * Expense command service interface.
 */
public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
    Optional<Expense> handle(UpdateExpenseCommand command);
    Optional<Expense> handle(DeleteExpenseCommand command);
}
