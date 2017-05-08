package tel_ran.security.accounts.repo;

import org.springframework.data.repository.CrudRepository;

import tel_ran.security.accounts.AccountData;

public interface AccountRepository extends CrudRepository<AccountData, String> {

}
