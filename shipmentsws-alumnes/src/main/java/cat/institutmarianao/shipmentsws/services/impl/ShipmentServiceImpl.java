package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.Shipment.Category;
import cat.institutmarianao.shipmentsws.repositories.ActionRepository;
import cat.institutmarianao.shipmentsws.repositories.ShipmentRepository;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import cat.institutmarianao.shipmentsws.specifications.SaveActionInShipment;
import cat.institutmarianao.shipmentsws.specifications.ShipmentInProcess;
import cat.institutmarianao.shipmentsws.specifications.ShipmentPending;
import cat.institutmarianao.shipmentsws.specifications.ShipmentTracker;
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
    private ActionRepository actionRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to) {
		return shipmentRepository.findAll();
    }

    @Override
    public List<Shipment> findAllPending(String receivedBy, String courierAssigned, Category category, Date from, Date to) {
    	Specification<Shipment> spec = new ShipmentPending(Shipment.Status.PENDING);
    	return shipmentRepository.findAll(spec);
    }

    @Override
    public List<Shipment> findAllInProcess(String receivedBy, String courierAssigned, Category category, Date from, Date to) {
    	Specification<Shipment> spec = Specification.where(new ShipmentInProcess(Shipment.Status.IN_PROCESS, receivedBy, courierAssigned, category, from, to));
    	return shipmentRepository.findAll(spec);
    }

    @Override
    public Shipment getByShipmentId(@Positive Long shipmentId) {
        return shipmentRepository.findById(shipmentId).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Action> findTrackingByTicketId(@Positive Long shipmentId) {
    	Specification<Action> spec = Specification.where(new ShipmentTracker(shipmentId));
    	return actionRepository.findAll(spec);
    }

    @Override
    @Validated(OnShipmentCreate.class)
    public Shipment saveShipment(@NotNull @Valid Shipment shipment) {
        return shipmentRepository.saveAndFlush(shipment);
    }

    @Override
    @Validated(OnActionCreate.class)
    public Action saveAction(@NotNull @Valid Action action) {
    	long idShipment = action.getIdShipment();
    	Specification<Shipment> spec = Specification.where(new SaveActionInShipment(idShipment, action));
        
        Shipment shipment = shipmentRepository.findOne(spec).orElseThrow();
        
        shipment = shipmentRepository.saveAndFlush(shipment);
        
        return action;
    }

    @Override
    public void deleteByShipmentId(@Positive Long shipmentId) {
        shipmentRepository.deleteById(shipmentId);
    }

}
