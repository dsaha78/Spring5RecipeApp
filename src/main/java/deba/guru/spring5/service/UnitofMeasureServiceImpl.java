package deba.guru.spring5.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import deba.guru.spring5.command.UnitOfMeasureCommand;
import deba.guru.spring5.converter.UnitOfMeasureToUnitOfMeasureCommand;
import deba.guru.spring5.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UnitofMeasureServiceImpl implements UnitoMeasureService {

	private final UnitOfMeasureRepository uomRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public UnitofMeasureServiceImpl(UnitOfMeasureRepository uomRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.uomRepository = uomRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		log.debug("uom Service called");
		return StreamSupport.stream(uomRepository.findAll().spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
	}

}
