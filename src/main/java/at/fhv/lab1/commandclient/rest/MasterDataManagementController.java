package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.service.MasterDataManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterDataManagementController {
    private final MasterDataManagementService masterDataManagementService;

    @Autowired
    public MasterDataManagementController(MasterDataManagementService masterDataManagementService) {
        this.masterDataManagementService = masterDataManagementService;
    }

    @CrossOrigin(origins = "http://localhost:8082")
    @PostMapping("/masterdata")
    public String createCommandAndQueryModels() {
        if (masterDataManagementService.createCommandAndQueryModels()) {
            return "Command and query models created successfully.";
        } else {
            return "Command and query models creation failed.";
        }
    }

    @DeleteMapping("/masterdata")
    public String deleteQueryModels() {
        if (masterDataManagementService.deleteModels()) {
            return "Models deleted successfully.";
        } else {
            return "Models deletion failed.";
        }
    }
}
