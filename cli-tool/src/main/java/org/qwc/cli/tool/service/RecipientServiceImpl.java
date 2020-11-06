package org.qwc.cli.tool.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.qwc.cli.tool.dao.RecipientEntity;
import org.qwc.cli.tool.dao.RecipientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipientServiceImpl implements RecipientService {

	@Autowired
	private RecipientRepository recipientRepository;

	@Transactional
	@Override
	public Recipient createRecipient(Recipient recipient) {

		// clear users before adding, limit to 1
		recipientRepository.deleteAll();

		RecipientEntity entity = new RecipientEntity();
		recipient.loadIntoEntity(entity);

		recipientRepository.saveAndFlush(entity);

		Recipient newRecipient = new Recipient();
		newRecipient.loadFromEntity(entity);

		return newRecipient;
	}

	@Transactional
	@Override
	public Recipient getRecipient() {

		Optional<RecipientEntity> recipientOpt = recipientRepository.findAll().stream().findFirst();

		if (recipientOpt.isPresent()) {
			Recipient recipient = new Recipient();
			recipient.loadFromEntity(recipientOpt.get());

			return recipient;
		}

		return null;
	}

}
