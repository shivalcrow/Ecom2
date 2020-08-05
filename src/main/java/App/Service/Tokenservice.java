package App.Service;

import java.util.List;
import java.util.Optional;

import App.entity.ConfirmationToken;

public interface Tokenservice {

	void deleteAll();

	void deleteAll(List<ConfirmationToken> entities);

	void delete(ConfirmationToken entity);

	void deleteById(String id);

	long count();

	List<ConfirmationToken> findAllById(List<String> ids);

	List<ConfirmationToken> findAll();

	boolean existsById(String id);

	Optional<ConfirmationToken> findById(String id);

	 List<ConfirmationToken> saveAll(List<ConfirmationToken> entities);

	ConfirmationToken save(ConfirmationToken entity);

	ConfirmationToken findByConfirmationToken(String confirmationToken);



	
	
}
