package cat.institutmarianao.shipmentsws.services;

import java.util.List;

import cat.institutmarianao.shipmentsws.model.Company;
import jakarta.validation.constraints.Positive;

public interface CompanyService {
	Company getByCompanyId(@Positive Long companyId);
	
	 List<Company> findAll();
}
