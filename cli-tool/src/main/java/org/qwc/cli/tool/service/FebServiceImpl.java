package org.qwc.cli.tool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.qwc.cli.tool.dao.FebEntity;
import org.qwc.cli.tool.dao.FebRepository;
import org.qwc.cli.tool.dao.SIEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FebServiceImpl implements FebService {
//wow
	@Autowired
	private FebRepository febRepository;



	@Transactional
	@Override
	public void createFeb(List<Feb> febList) {

		// clear sis before adding
		febRepository.deleteAll();

		List<FebEntity> febEntityList = new ArrayList<>();

		for(Feb feb : febList) {
			FebEntity entity = new FebEntity();
			feb.loadIntoEntity(entity);
			febEntityList.add(entity);
		}


		febRepository.saveAll(febEntityList);
	}

	@Override
	public boolean findByMsisdn(String msisdn) {

		Optional<FebEntity> febEntity = febRepository.findByMsisdn(msisdn);
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
