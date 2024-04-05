package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.service.MasterDataManagementService;
import at.fhv.lab1.eventbus.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MasterDataManagementController {
    private final MasterDataManagementService masterDataManagementService;

    @Autowired
    public MasterDataManagementController(MasterDataManagementService masterDataManagementService) {
        this.masterDataManagementService = masterDataManagementService;
    }

    @DeleteMapping("/query-models")
    public String deleteQueryModels() {

        return "Query models deleted successfully.";
    }

    @PostMapping("/restore-from-events")
    public String restoreFromEvents() {
        masterDataManagementService.restoreFromEvents();
        return "System state restored from events.";
    }

    @GetMapping("/all-events")
    public List<Event> getAllEvents() {
        return new ArrayList<>();
    }

    @GetMapping("/generateMasterData")
    public String createCommandAndQueryModels() {
        masterDataManagementService.createCommandAndQueryModels();
        return "Command and query models created successfully.";
    }
}
