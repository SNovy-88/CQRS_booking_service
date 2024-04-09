package at.fhv.lab1.commandclient.service;

public interface IMasterDataManagementService {
    Boolean restoreFromEvents();

    Boolean createCommandAndQueryModels();

    Boolean deleteModels();
}
