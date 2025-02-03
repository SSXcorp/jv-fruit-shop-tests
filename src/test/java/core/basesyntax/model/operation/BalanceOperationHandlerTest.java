package core.basesyntax.model.operation;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.model.OperationHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BalanceOperationHandlerTest {
    private final Storage currentStorage = new Storage();
    private final OperationHandler operationHandler = new BalanceOperationHandler(currentStorage);

    @AfterEach
    public void tearDown() {
        currentStorage.getInventory().clear();
    }

    @Test
    public void handle_validValues_Ok() {
        FruitTransaction transaction = new FruitTransaction();
        transaction.setOperation(FruitTransaction.Operation.BALANCE);
        transaction.setFruit("Grapes");
        transaction.setQuantity(10);
        operationHandler.handle(transaction);

        assertTrue(currentStorage.getInventory().containsKey("Grapes"));
        assertTrue(currentStorage.getInventory().containsValue(10));
    }

    @Test
    public void handle_fruitAlreadyInStorageValid_Ok() {
        FruitTransaction first = new FruitTransaction();
        first.setOperation(FruitTransaction.Operation.BALANCE);
        first.setFruit("Grapes");
        first.setQuantity(10);
        currentStorage.getInventory().put(first.getFruit(), first.getQuantity());

        FruitTransaction second = new FruitTransaction();
        second.setOperation(FruitTransaction.Operation.BALANCE);
        second.setFruit("Grapes");
        second.setQuantity(25);
        operationHandler.handle(second);

        assertTrue(currentStorage.getInventory().containsKey("Grapes"));
        assertTrue(currentStorage.getInventory().containsValue(25));
    }
}
