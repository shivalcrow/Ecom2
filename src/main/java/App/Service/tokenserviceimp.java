package App.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import App.Repository.ConfirmationTokenRepository;
import App.entity.ConfirmationToken;

@Service
public class tokenserviceimp  implements Tokenservice{
	private ConfirmationTokenRepository confirmtoken;

	@Override
	public ConfirmationToken findByConfirmationToken(String confirmationToken) {
		return confirmtoken.findByconfirmationToken(confirmationToken);
	}

	@Override
	public ConfirmationToken  save(ConfirmationToken entity) {
		return confirmtoken.save(entity);
	}

	@Override
	public  List<ConfirmationToken>  saveAll(List<ConfirmationToken> entities) {
		return (List<ConfirmationToken>) confirmtoken.saveAll(entities);
	}

	@Override
	public Optional<ConfirmationToken> findById(String id) {
		return confirmtoken.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return confirmtoken.existsById(id);
	}

	@Override
	public List<ConfirmationToken> findAll() {
		return (List<ConfirmationToken>) confirmtoken.findAll();
	}

	@Override
	public List<ConfirmationToken> findAllById(List<String> ids) {
		return (List<ConfirmationToken>) confirmtoken.findAllById(ids);
	}

	@Override
	public long count() {
		return confirmtoken.count();
	}

	@Override
	public void deleteById(String id) {
		confirmtoken.deleteById(id);
	}

	@Override
	public void delete(ConfirmationToken entity) {
		confirmtoken.delete(entity);
	}

	@Override
	public void deleteAll(List<ConfirmationToken> entities) {
		confirmtoken.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		confirmtoken.deleteAll();
	}


}
