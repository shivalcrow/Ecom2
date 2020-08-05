package App.Repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import App.entity.ConfirmationToken;


@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByconfirmationToken(String confirmationToken);
}

