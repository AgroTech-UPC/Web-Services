package com.acme.web.services.management.application.internal.commandservices;

import com.acme.web.services.management.domain.model.aggregates.Expense;
import com.acme.web.services.management.domain.model.commands.CreateExpenseCommand;
import com.acme.web.services.management.domain.model.commands.DeleteExpenseCommand;
import com.acme.web.services.management.domain.model.commands.UpdateExpenseCommand;
import com.acme.web.services.management.domain.model.valueobjects.*;
import com.acme.web.services.management.domain.services.ExpenseCommandService;
import com.acme.web.services.management.infrastructure.persitence.jpa.repositories.ExpenseRepository;
import com.acme.web.services.user.infrastructure.persistence.jpa.repositories.BreederRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the ExpenseCommandService interface
 */
@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {
    private final ExpenseRepository expenseRepository;
    private final BreederRepository breederRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, BreederRepository breederRepository){
        this.expenseRepository = expenseRepository;
        this.breederRepository = breederRepository;
    }

    /**
     * Creates an expense in the database
     * @param command the command to create an expense
     * @return the created expense
     */

    @Override
    public Long handle(CreateExpenseCommand command) {
        var breeder = breederRepository.findById(command.breederId());
        if (breeder.isEmpty()){
            throw new IllegalArgumentException("Breeder does not exist");
        }
        var expense = new Expense(command, breeder.get());
        try {
            expenseRepository.save(expense);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving expense: " + e.getMessage());
        }
        return expense.getId();
    }

    /**
     * Updates an expense in the database
     * @param command the command to update an expense
     * @return the updated expense
     */
    @Override
    public Optional<Expense> handle(UpdateExpenseCommand command) {
        return expenseRepository.findById(command.expenseId()).map(expense -> {
            expense.setName(new Name(command.name()));
            expense.setExpenseType(ExpenseType.valueOf(command.type().toUpperCase()));
            expense.setAmount(new Amount(command.amount()));
            expense.setDate(new DateOfCreation(command.date()));
            expense.setObservations(new Observations(command.observations()));
            return expenseRepository.save(expense);
        });
    }

    /**
     * Deletes an expense in the database
     * @param command the command to delete an expense
     * @return the deleted expense
     */
    @Override
    public Optional<Expense> handle(DeleteExpenseCommand command) {
        if (!expenseRepository.existsById(command.expenseId())) {
            throw new IllegalArgumentException("Expense does not exist");
        }
        var expense = expenseRepository.findById(command.expenseId());
        expense.ifPresent(expenseRepository::delete);
        return expense;
    }
}