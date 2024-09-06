package com.nxc.controllers;

import com.nxc.dto.carrier.respone.CarrierResponseDTO;
import com.nxc.dto.order.response.OrderResponseDTO;
import com.nxc.dto.shipment.response.ShipmentResponseDTO;
<<<<<<< HEAD
import com.nxc.service.CarrierService;
import com.nxc.service.OrderService;
import com.nxc.service.ShipmentService;
import com.nxc.service.UserService;
import com.nxc.service.WarehouseService;
=======
import com.nxc.dto.supplier.request.SupplierPerformanceDTO;
import com.nxc.dto.supplier.request.SupplierPerformanceRequestDTO;
import com.nxc.dto.supplier.request.SupplierRatingRequestDTO;
import com.nxc.dto.supplier.response.SupplierRatingResponseDTO;
import com.nxc.dto.user.response.UserResponseDTO;
import com.nxc.enums.CriteriaEnum;
import com.nxc.service.*;
>>>>>>> origin/lan
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final OrderService orderService;
    private final WarehouseService warehouseService;
    private final ShipmentService shipmentService;
    private final CarrierService carrierService;
    private final InventoryService inventoryService;
    private final SupplierRatingService supplierRatingService;
    private final SupplierPerformanceService supplierPerformanceService;


    @GetMapping
    public String homePage() {
        return "index";
    }

    @GetMapping("/accounts")
    public String listAccounts(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("users", this.userService.findUser(params));

        return "account";
    }

    @PostMapping("/accounts")
    public String confirmUser(@RequestParam("id") Long id) {
        this.userService.confirmUser(id);
        return "redirect:/admin/accounts";
    }

    @PostMapping("/accounts/delete")
    public String deleteUser(@RequestParam("userId") Long userId) {
        this.userService.deleteUser(userId);
        return "redirect:/admin/accounts";
    }

    @GetMapping("/orders")
    public String viewOrders(@RequestParam Map<String, String> params, Model model) {
        List<OrderResponseDTO> orders = orderService.getAllOrders(params);
        model.addAttribute("orders", orders);

        for (OrderResponseDTO order : orders) {
            String userFullName = orderService.getUserFullName(order.getUserId());
            model.addAttribute("userFullName" + order.getId(), userFullName);
        }

        return "order";
    }

    @GetMapping("/orders/{orderId}")
    public String viewOrderDetail(@PathVariable Long orderId, Model model) {
        OrderResponseDTO order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "orderDetails";
    }

    @PostMapping("/orders/{orderId}/confirm")
    public String confirmOrder(@PathVariable Long orderId) {
        Long adminUserId = 1L;
        orderService.confirmOrder(orderId, adminUserId);
        return "redirect:/admin/orders";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        Long adminUserId = 1L;
        orderService.cancelOrder(orderId, adminUserId);
        return "redirect:/admin/orders";
    }

    @GetMapping("/warehouse")
    public String viewWarehouse(Model model) {
        model.addAttribute("warehouse", this.warehouseService.getAllWarehouses());
        return "warehouse";
    }

    @PostMapping("/warehouse/{warehouseId}/delete")
    public String deleteWarehouse(@PathVariable Long warehouseId) {
        warehouseService.deleteWarehouse(warehouseId);
        return "redirect:/admin/warehouse";
    }

    @GetMapping("/shipments")
    public String viewShipments(Model model) {
        List<ShipmentResponseDTO> shipments = shipmentService.getAllShipments();
        model.addAttribute("shipments", shipments);
        return "shipment";
    }

    @GetMapping("/shipments/{shipmentId}")
    public String viewShipmentDetail(@PathVariable Long shipmentId, Model model) {
        ShipmentResponseDTO shipment = shipmentService.getShipmentDetails(shipmentId);
        model.addAttribute("shipment", shipment);
        return "shipmentDetails";
    }

    @PostMapping("/shipments/{id}/intransit")
    public String intransitShipment(@PathVariable Long id) {
        shipmentService.inTransitShipment(id);
        return "redirect:/admin/shipments";
    }

    @PostMapping("/shipments/{id}/done")
    public String doneShipment(@PathVariable Long id) {
        shipmentService.doneShipment(id);
        return "redirect:/admin/shipments";
    }

    @PostMapping("/shipments/{id}/cancel")
    public String cancelShipment(@PathVariable Long id) {
        shipmentService.cancelShipment(id);
        return "redirect:/admin/shipments";
    }

    @GetMapping("/carriers")
    public String listCarriers(Model model) {
        List<CarrierResponseDTO> carriers = carrierService.getAllCarriers();
        model.addAttribute("carriers", carriers);
        return "carrier";
    }

    @GetMapping("/carriers/{carrierId}")
    public String viewCarrierDetail(@PathVariable Long carrierId, Model model) {
        CarrierResponseDTO carrier = carrierService.getCarrierDetails(carrierId);
        model.addAttribute("carrier", carrier);
        return "carrierDetails";
    }

    @GetMapping("/suppliers")
    public String listSuppliers(Model model) {
        List<UserResponseDTO> suppliers = userService.getAllSuppliers();
        model.addAttribute("suppliers", suppliers);
        return "supplierList";
    }

    @GetMapping("/suppliers/{supplierId}/ratings")
    public String viewSupplierRatings(@PathVariable Long supplierId, Model model) {
        List<SupplierRatingResponseDTO> ratings = supplierRatingService.getRatingsBySupplier(supplierId);

        model.addAttribute("ratings", ratings);
        model.addAttribute("supplierId", supplierId);
        model.addAttribute("criteria", CriteriaEnum.values());

        return "supplierRatings";
    }

    @PostMapping("/suppliers/{supplierId}/ratings")
    public String rateSupplier(@PathVariable Long supplierId, SupplierRatingRequestDTO dto) {
        dto.setSupplierId(supplierId);
        supplierRatingService.rateSupplier(dto);
        return "redirect:/admin/suppliers/";
    }

    @GetMapping("/inventory-report")
    public String viewInventoryReport(Model model) {
        model.addAttribute("inventory", inventoryService.getInventoryReport());
        return "inventoryReport";
    }

    @GetMapping("/suppliers/performance")
    public String viewSupplierPerformance(Model model) {
        List<SupplierPerformanceDTO> performanceList = supplierPerformanceService.getSupplierPerformanceReport();
        System.out.println(performanceList);
        model.addAttribute("performanceList", performanceList);
        return "supplierPerformance";
    }
}
