package cat.institutmarianao.shipmentsws.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Company;
import cat.institutmarianao.shipmentsws.repositories.CompanyRepository;
import cat.institutmarianao.shipmentsws.services.CompanyService;
import jakarta.validation.constraints.Positive;

@Service
public class CompanyServiceImpl implements CompanyService{

	private final CompanyRepository companyRepository;
	
	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	@Override
	public Company getByCompanyId(@Positive Long companyId) {
		return companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}

}
