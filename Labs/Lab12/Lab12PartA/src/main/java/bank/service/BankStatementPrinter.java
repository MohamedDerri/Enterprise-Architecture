package bank.service;



import bank.service.AccountDTO;
import bank.service.AccountEntryDTO;
import bank.service.AccountService;
import bank.service.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BankStatementPrinter {

    @Autowired
    AccountService accountService;



    @Scheduled(fixedRate = 20000)
    public void print(){
        CustomerDTO customer = null;
        for (AccountDTO account :   accountService.getAllAccounts()) {
            customer = account.getCustomer();
            System.out.println("Account number : " + account.getAccountnumber());
            System.out.println("Account owner: " + customer.getName());
            System.out.printf("Current sold is :", account.getBalance());
        }
    }

}
