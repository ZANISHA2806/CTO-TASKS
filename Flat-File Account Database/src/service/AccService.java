package service;

import model.Account;
import enums.AccountType;
import enums.UpdateType;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import util.LoggerUtil;
import java.util.List;
import java.util.ArrayList;
import service.AccountOperations;
import exception.DuplicateKeyException;

public class AccService implements AccountOperations{

    private static final String filepath =
            "src/data/SampleAccount.txt";

    public static final File file =
            new File(filepath);
    String line;

    private void ensureFileExists()
            throws IOException {

        if (!file.exists()) {

            LoggerUtil.info(
                    "file does not exist, creating new file"
            );

            file.createNewFile();

            LoggerUtil.info(
                    "new file created"
            );
        }
    }

    private BufferedReader Reader() throws IOException{
    	
    	return new BufferedReader(new FileReader(filepath));
    }
    
    
   
	
	@Override
	public void create(Account account)
	        throws DuplicateKeyException {

	    try {

	        ensureFileExists();

	        Account existing =
	                read(account.getAccId());

	        if (existing != null) {

	            throw new DuplicateKeyException(
	                    "Account ID already exists: "
	                            + account.getAccId()
	            );
	        }

	        FileWriter writer =
	                new FileWriter(file, true);

	        writer.write(
	                account.toFileRecord()
	        );

	        writer.write(
	                System.lineSeparator()
	        );

	        writer.close();

	        LoggerUtil.info(
	                "account created successfully "
	                        + account.getAccId()
	        );

	    } catch (IOException e) {

	        LoggerUtil.error(
	                e.getMessage()
	        );
	    }
	}

	@Override
	public Account read(String id) {

	    try {

	        ensureFileExists();

	        try (BufferedReader reader =
	                     Reader()) {

	            String line;

	            while ((line = reader.readLine()) != null) {

	                if (line.trim().isEmpty()) {
	                    continue;
	                }

	                Account account =
	                        Account.fromFileRecord(line);

	                if (account == null) {
	                    continue;
	                }
	                if (account.getAccId()
	                        .equals(id)) {

	                    LoggerUtil.info(
	                            "account found "
	                                    + account.getAccId() +" | "+account.getHoldername()+" | "+account.getBalance()+" | "+account.getAccType()+" | "+account.getStatus()
	                    );

	                    return account;
	                }
	            }
	        }

	        LoggerUtil.error(
	                "account not found "
	                        + id
	        );

	    } catch (IOException e) {

	        LoggerUtil.error(
	                e.getMessage()
	        );
	    }

	    return null;
	}

	@Override
	public void update(String id, UpdateType type, String value) {

	    File tempFile = new File("src/data/tempacc.txt");

	    boolean found = false;

	    try {

	        ensureFileExists();

	        BufferedReader reader = Reader();
	        FileWriter writer = new FileWriter(tempFile, false);

	        String line;

	        while ((line = reader.readLine()) != null) {

	            if (line.trim().isEmpty()) continue;

	            Account account = Account.fromFileRecord(line);

	            if (account == null) continue;

	            if (account.getAccId().equals(id)) {

	                found = true;

	                switch (type) {

	                    case HOLDERNAME:
	                        account = new Account(
	                                account.getAccId(),
	                                value,
	                                account.getBalance(),
	                                account.getAccType(),
	                                account.getStatus()
	                        );
	                        break;

	                    case ACCTYPE:
	                        account = new Account(
	                                account.getAccId(),
	                                account.getHoldername(),
	                                account.getBalance(),
	                                AccountType.valueOf(value.toUpperCase()),
	                                account.getStatus()
	                        );
	                        break;

	                    case CREDIT:
	                        account = new Account(
	                                account.getAccId(),
	                                account.getHoldername(),
	                                account.getBalance() + Double.parseDouble(value),
	                                account.getAccType(),
	                                account.getStatus()
	                        );
	                        break;

	                    case DEBIT:
	                        account = new Account(
	                                account.getAccId(),
	                                account.getHoldername(),
	                                account.getBalance() - Double.parseDouble(value),
	                                account.getAccType(),
	                                account.getStatus()
	                        );
	                        break;
	                }

	                LoggerUtil.info("Updated account: " + id);
	            }

	            writer.write(account.toFileRecord());
	            writer.write(System.lineSeparator());
	        }

	        reader.close();
	        writer.close();

	        if (file.delete()) {
	            tempFile.renameTo(file);
	        }

	        if (!found) {
	            LoggerUtil.error("Account not found: " + id);
	        }

	    } catch (IOException e) {
	        LoggerUtil.error("Update error: " + e.getMessage());
	    }
	}

	@Override
	public void delete(String id, String holdername) {

	    File tempFile = new File("src/data/tempacc.txt");

	    boolean found = false;

	    try {

	        ensureFileExists();

	        BufferedReader reader = Reader();
	        FileWriter writer = new FileWriter(tempFile, false);

	        String line;

	        while ((line = reader.readLine()) != null) {

	            if (line.trim().isEmpty()) continue;

	            Account account = Account.fromFileRecord(line);

	            if (account == null) continue;

	            if (account.getAccId().equals(id)
	                    && account.getHoldername().equals(holdername)) {

	                found = true;
	                LoggerUtil.info("Deleting account: " + id);
	                continue; 
	            }

	            writer.write(account.toFileRecord());
	            writer.write(System.lineSeparator());
	        }

	        reader.close();
	        writer.close();

	        if (file.delete()) {
	            tempFile.renameTo(file);
	        }

	        if (!found) {
	            LoggerUtil.error("Account not found: " + id);
	        }

	    } catch (IOException e) {
	        LoggerUtil.error("Delete error: " + e.getMessage());
	    }
	}
	@Override
	public List<Account> listAll() {

	    List<Account> accounts =
	            new ArrayList<>();

	    try {

	        ensureFileExists();

	        BufferedReader reader =
	                Reader();

	        String line;

	        while ((line = reader.readLine()) != null) {

	            if (line.trim().isEmpty()) {
	                continue;
	            }

	            Account account =
	                    Account.fromFileRecord(line);

	            if (account != null) {
	                accounts.add(account);
	            }
	        }

	        reader.close();

	        LoggerUtil.info(
	                "listed all accounts successfully"
	        );

	    } catch (IOException e) {

	        LoggerUtil.error(
	                "error while listing accounts: "
	                        + e.getMessage()
	        );
	    }

	    return accounts;
	}
}