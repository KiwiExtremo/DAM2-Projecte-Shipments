package cat.institutmarianao.shipmentsws.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Office;
import cat.institutmarianao.shipmentsws.repositories.OfficeRepository;
import cat.institutmarianao.shipmentsws.services.OfficeService;
import jakarta.validation.constraints.Positive;

@Service
public class OfficeServiceImpl implements OfficeService{

	private final OfficeRepository officeRepository;
	
	public OfficeServiceImpl(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}
	
	@Override
	public Office getByOfficeId(@Positive Long officeId) {
		return officeRepository.findById(officeId).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Office> findAll() {
		return officeRepository.findAll();
	}

}
