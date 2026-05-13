package service;
import enums.UpdateType;
import exception.DuplicateKeyException;
import model.Account;
import java.util.List;
public interface AccountOperations {

    void create(Account account)throws DuplicateKeyException;

    Account read(String id);

    void update(String id, UpdateType type, String value);

    void delete(String id,String holdername);

    List<Account> listAll();
}