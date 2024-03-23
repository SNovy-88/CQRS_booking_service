package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.repository.BookingRepository;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.commandclient.repository.RoomRepository;
import at.fhv.lab1.commandclient.service.MasterDataManagementService;
import at.fhv.lab1.eventbus.repository.EventRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan({"at.fhv.lab1.eventbus", "at.fhv.lab1.commandclient"})
public class CommandClientApplication {

    private final EventPublisher publisher;

    public CommandClientApplication(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public static void main(String[] args) {
        SpringApplication.run(CommandClientApplication.class, args);
    }

    @Bean
    public MasterDataManagementService masterDataManagementService(EventRepository eventRepository, RoomRepository roomRepository, CustomerRepository customerRepository, BookingRepository bookingRepository) {
        return new MasterDataManagementService(publisher, roomRepository, customerRepository, bookingRepository);
    }

//    @Bean
//    public CommandLineRunner run() throws Exception {
//        return args -> {
//            Event event = new Event();
//            event.setContent("This is the content!");
//            event.setCustomer(1);
//            event.setTimestamp(System.currentTimeMillis());
//            System.out.println("Result: " + publisher.publishEvent(event));
//        };
//    }
}
