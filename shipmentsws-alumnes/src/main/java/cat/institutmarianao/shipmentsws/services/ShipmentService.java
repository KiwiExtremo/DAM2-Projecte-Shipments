package cat.institutmarianao.shipmentsws.services;

import java.util.Date;
import java.util.List;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public interface ShipmentService {
    List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to);

    List<Shipment> findAllPending(String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to);
    
    List<Shipment> findAllInProcess(String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to);
    
    Shipment getByShipmentId(@Positive Long shipmentId);
    
    List<Action> findTrackingByShipmentId(@Positive Long shipmentId);

    Shipment saveShipment(@NotNull @Valid Shipment shipment);
    
    Action saveAction(@NotNull @Valid Action action);

    void deleteByShipmentId(Long id);
}