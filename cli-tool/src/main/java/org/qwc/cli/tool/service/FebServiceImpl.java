package org.qwc.cli.tool.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.qwc.cli.tool.dao.FebEntity;
import org.qwc.cli.tool.dao.FebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FebServiceImpl implements FebService {

	@Autowired
	private FebRepository febRepository;

	@Override
	public void clearFeb() {
		// clear febs before adding
		febRepository.deleteAll();
	}

	@Transactional
	@Override
	public Feb createFeb(Feb feb) {

		FebEntity entity = new FebEntity();
		feb.loadIntoEntity(entity);

		febRepository.saveAndFlush(entity);

		Feb newFeb = new Feb();
		newFeb.loadFromEntity(entity);

		return newFeb;
	}

	@Override
	public boolean findByID(Long id) {

		Optional<FebEntity> febEntity = febRepository.findById(id);
		return febEntity.isPresent();

	}

	@Transactional
	@Override
	public Feb getFeb() {

		Optional<FebEntity> febOpt = febRepository.findAll().stream().findFirst();

		if (febOpt.isPresent()) {
			Feb feb = new Feb();
			feb.loadFromEntity(febOpt.get());

			return feb;
		}

		return null;
	}

}
