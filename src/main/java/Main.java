import com.app.model.base.BaseEntity;
import com.app.model.embeddable.Registration;
import com.app.model.entity.Car;
import com.app.model.entity.Truck;
import com.app.model.enums.VehicleStatus;
import com.app.repository.CarRepository;
import com.app.service.AllVehiclesService;
import com.app.service.VehicleService;
import com.app.service.impl.AllVehiclesServiceImpl;
import com.app.service.impl.CarService;
import com.app.service.impl.TruckService;
import com.app.utils.PrintUtils;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgresql");
        CarService carService = new CarService(emf);
        TruckService truckService = new TruckService(emf);
        AllVehiclesService<Long> allVehiclesService = new AllVehiclesServiceImpl(emf);

        createSomeVehicles(carService, truckService);
        executeCrudMethodsTest(carService, truckService, allVehiclesService);
    }

    public static void createSomeVehicles(CarService carService, TruckService truckService) {
        Registration reg1 = new Registration()
                .state("CA")
                .plateNumber("ABC-123")
                .registrationDate(LocalDate.of(2022, 5, 15));

        Registration reg2 = new Registration()
                .state("TX")
                .plateNumber("XYZ-987")
                .registrationDate(LocalDate.of(2021, 11, 3));

        Registration reg3 = new Registration()
                .state("NY")
                .plateNumber("NY-4567")
                .registrationDate(LocalDate.of(2023, 2, 20));

        Registration reg4 = new Registration()
                .state("FL")
                .plateNumber("FL-2025")
                .registrationDate(LocalDate.of(2020, 7, 1));

        Car car1 = new Car()
                .brand("Toyota")
                .model("Corolla")
                .year(2020)
                .registration(reg1)
                .status(VehicleStatus.ACTIVE)
                .seatingCapacity(5)
                .fuelType("Petrol");

        Car car2 = new Car()
                .brand("Tesla")
                .model("Model 3")
                .year(2023)
                .registration(reg2)
                .status(VehicleStatus.ACTIVE)
                .seatingCapacity(5)
                .fuelType("Electric");

        Car car3 = new Car()
                .brand("Honda")
                .model("Civic")
                .year(2019)
                .registration(reg3)
                .status(VehicleStatus.MAINTENANCE)
                .seatingCapacity(4)
                .fuelType("Hybrid");

        Truck truck1 = new Truck()
                .brand("Volvo")
                .model("FH16")
                .year(2021)
                .registration(reg4)
                .status(VehicleStatus.ACTIVE)
                .loadCapacity(18.0)
                .numberOfAxles(3);

        Truck truck2 = new Truck()
                .brand("Mercedes")
                .model("Actros")
                .year(2022)
                .registration(reg2)
                .status(VehicleStatus.INACTIVE)
                .loadCapacity(12.5)
                .numberOfAxles(2);

        Truck truck3 = new Truck()
                .brand("Ford")
                .model("F-Max")
                .year(2020)
                .registration(reg1)
                .status(VehicleStatus.MAINTENANCE)
                .loadCapacity(15.0)
                .numberOfAxles(2);

        carService.create(car1);
        carService.create(car2);
        carService.create(car3);

        truckService.create(truck1);
        truckService.create(truck2);
        truckService.create(truck3);
    }

    public static void executeCrudMethodsTest(CarService carService, TruckService truckService, AllVehiclesService<Long> allVehiclesService) {
        carService.update(1L, Map.of("model", "TEST_TEST", "year", 2002, "brand", "TEST_BRAND"));
//        truckService.update(1L, Map.of("model", "TEST_TEST", "year", 2002));
        truckService.removeById(6L);

        PrintUtils.printList("All Cars List", carService.findAll());
        PrintUtils.printList("Trucks By Brand 'Volvo'", truckService.findByBrand("volvo"));
        PrintUtils.printList("Car By ID '3'", List.of(carService.findById(3L)));
        PrintUtils.printList("All Vehicles by AllVehiclesService", allVehiclesService.findAll()
                .stream()
                .sorted(Comparator.comparing(BaseEntity::getId))
                .toList()
        );
    }
}
