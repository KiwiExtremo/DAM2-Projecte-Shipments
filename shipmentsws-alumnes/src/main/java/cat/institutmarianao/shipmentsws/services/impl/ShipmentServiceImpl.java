package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.Shipment.Category;
import cat.institutmarianao.shipmentsws.repositories.ShipmentRepository;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import cat.institutmarianao.shipmentsws.specifications.ShipmentInProgress;
import cat.institutmarianao.shipmentsws.specifications.ShipmentPending;
import cat.institutmarianao.shipmentsws.specifications.UserWithFullName;
import cat.institutmarianao.shipmentsws.specifications.UserWithRole;
import cat.institutmarianao.shipmentsws.validation.groups.OnActionCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnShipmentCreate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
@Service
public class ShipmentServiceImpl implements ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to) {
		return shipmentRepository.findAll();
    }

    @Override
    public List<Shipment> findAllPending(String receivedBy, String courierAssigned, Category category, Date from, Date to) {
    	Specification<Shipment> pendingSpec = new ShipmentPending(Shipment.Status.PENDING);
    	return shipmentRepository.findAll(pendingSpec);
    }

    @Override
    public List<Shipment> findAllInProcess(String receivedBy, String courierAssigned, Category category, Date from, Date to) {
    	Specification<Shipment> spec = Specification.where(new ShipmentInProgress(Shipment.Status.IN_PROCESS));
    	return shipmentRepository.findAll(spec);
    }

    @Override
    public Shipment getByShipmentId(@Positive Long shipmentId) {
        return shipmentRepository.findById(shipmentId).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Action> findTrackingByShipmentId(@Positive Long shipmentId) {
        // TODO: Implement logic to find tracking actions for a shipment
        return null;
    }

    @Override
    @Validated(OnShipmentCreate.class)
    public Shipment saveShipment(@NotNull @Valid Shipment shipment) {
        return shipmentRepository.saveAndFlush(shipment);
    }

    @Override
    @Validated(OnActionCreate.class)
    public Action saveAction(@NotNull @Valid Action action) {
        // TODO: Implement logic to save an action for a shipment
        return null;
    }

    @Override
    public void deleteByShipmentId(@Positive Long shipmentId) {
        shipmentRepository.deleteById(shipmentId);
    }

}
