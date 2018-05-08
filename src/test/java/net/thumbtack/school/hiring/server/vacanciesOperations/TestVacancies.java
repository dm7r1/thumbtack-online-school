package net.thumbtack.school.hiring.server.vacanciesOperations;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.data.models.RequirementProperties;
import net.thumbtack.school.hiring.data.models.RequirementsList;
import net.thumbtack.school.hiring.data.models.Vacancy;
import net.thumbtack.school.hiring.data.models.requests.AddVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.ChangeVacancyDtoRequest;
import net.thumbtack.school.hiring.data.models.requests.DtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.requests.SpecialDtoRequestsFactory;
import net.thumbtack.school.hiring.data.models.responses.ErrorDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessEmptyDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.SuccessfulRegisteredDtoResponse;
import net.thumbtack.school.hiring.data.models.responses.VacanciesListDtoResponse;
import net.thumbtack.school.hiring.server.Server;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestVacancies {
    @Test
    public void addVacancy() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        String responseJson = server.addVacancy(gson.toJson(SpecialDtoRequestsFactory.makeValidAddVacancyDtoRequest(token)));
        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());

        server.stopServer("");
    }

    @Test
    public void addInvalidVacancy() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        AddVacancyDtoRequest requestWithInvalidVacancyName = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "", 1, new RequirementsList()
        );
        AddVacancyDtoRequest requestWithInvalidPaymentNumber = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancyName", 0, new RequirementsList()
        );
        AddVacancyDtoRequest requestWithInvalidPaymentNull = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancyName", null, new RequirementsList()
        );
        RequirementsList requirementsWithInvalidNames = new RequirementsList();
        requirementsWithInvalidNames.addRequirement("", new RequirementProperties(1, false));
        AddVacancyDtoRequest requestWithInvalidRequirementsNames = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancyName", 1, requirementsWithInvalidNames
        );
        RequirementsList requirementsWithInvalidLvls = new RequirementsList();
        requirementsWithInvalidLvls.addRequirement("req", new RequirementProperties(6, false));
        AddVacancyDtoRequest requestWithInvalidRequirementsLvls = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancyName", 1, requirementsWithInvalidLvls
        );
        AddVacancyDtoRequest invalidRequests[] = {
                requestWithInvalidVacancyName, requestWithInvalidPaymentNumber, requestWithInvalidPaymentNull, requestWithInvalidRequirementsNames, requestWithInvalidRequirementsLvls
        };
        for(AddVacancyDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.addVacancy(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }

    @Test
    public void deleteVacancy() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        server.addVacancy(gson.toJson(SpecialDtoRequestsFactory.makeValidAddVacancyDtoRequest(token)));

        String responseJson = server.deleteVacancy(gson.toJson(DtoRequestsFactory.makeDeleteVacancyDtoRequest(token, 0)));
        assertEquals("success", gson.fromJson(responseJson, SuccessEmptyDtoResponse.class).getResult());

        server.stopServer("");
    }

    @Test
    public void deleteNonexistentVacancy() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        String responseJson = server.deleteVacancy(gson.toJson(DtoRequestsFactory.makeDeleteVacancyDtoRequest(token, 0)));
        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class));

        responseJson = server.deleteVacancy(gson.toJson(DtoRequestsFactory.makeDeleteVacancyDtoRequest(token, -1)));
        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class));

        responseJson = server.deleteVacancy(gson.toJson(DtoRequestsFactory.makeDeleteVacancyDtoRequest(UUID.randomUUID(), 0)));
        assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class));

        server.stopServer("");
    }

    @Test
    public void getVacanciesList() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirements1 = new RequirementsList();
        requirements1.addRequirement("java", new RequirementProperties(3, true));
        requirements1.addRequirement("android", new RequirementProperties(4, true));
        RequirementsList requirements2 = new RequirementsList();
        requirements2.addRequirement("ML", new RequirementProperties(4, true));
        requirements2.addRequirement("python", new RequirementProperties(2, false));
        List<Vacancy> vacanciesList = new ArrayList<>();
        vacanciesList.add(new Vacancy("vacancy1", 1, requirements1));
        vacanciesList.add(new Vacancy("vacancy2", 2, requirements2));
        for(Vacancy vacancy: vacanciesList) {
            server.addVacancy(gson.toJson(DtoRequestsFactory.makeAddVacancyDtoRequest(
                    token, vacancy.getVacancyName(), vacancy.getPayment(), vacancy.getRequirements()
            )));
        }
        String responseJson = server.getVacancies(gson.toJson(DtoRequestsFactory.makeGetVacanciesDtoRequest(token)));
        List<Vacancy> receivedVacanciesList = gson.fromJson(responseJson, VacanciesListDtoResponse.class).getVacancies();

        assertEquals(vacanciesList.size(), receivedVacanciesList.size());
        for(int i = 0; i < vacanciesList.size(); ++i) {
            assertEquals(vacanciesList.get(i).getVacancyName(), receivedVacanciesList.get(i).getVacancyName());
            assertEquals(vacanciesList.get(i).getPayment(), receivedVacanciesList.get(i).getPayment());

            RequirementsList expectedRequirements = vacanciesList.get(i).getRequirements();
            RequirementsList actualRequirements = receivedVacanciesList.get(i).getRequirements();
            assertEquals(expectedRequirements.size(), actualRequirements.size());
            for(String requirementName: expectedRequirements.getRequirementsNamesSet()) {
                assertEquals(expectedRequirements.getRequirementProperties(requirementName).getLvl(), actualRequirements.getRequirementProperties(requirementName).getLvl());
                assertEquals(expectedRequirements.getRequirementProperties(requirementName).isNecessary(), actualRequirements.getRequirementProperties(requirementName).isNecessary());
            }
        }

        server.stopServer("");
    }

    @Test
    public void changeVacancy() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirements = new RequirementsList();
        requirements.addRequirement("req1", new RequirementProperties(3, false));
        requirements.addRequirement("req2", new RequirementProperties(4, true));
        requirements.addRequirement("req3", new RequirementProperties(2, false));
        requirements.addRequirement("thisRequirementWillBeChanged", new RequirementProperties(3, false));
        server.addVacancy(gson.toJson(
                DtoRequestsFactory.makeAddVacancyDtoRequest(token, "vacancy1", 1, requirements)
        ));

        RequirementsList newRequirementsForAdding = new RequirementsList();
        newRequirementsForAdding.addRequirement("newReq1", new RequirementProperties(2, false));
        newRequirementsForAdding.addRequirement("newReq2", new RequirementProperties(1, true));
        newRequirementsForAdding.addRequirement("thisRequirementWillBeChanged", new RequirementProperties(1, true));
        Set<String> requirementsForDeletingNames = new HashSet<>();
        requirementsForDeletingNames.add("req1");
        requirementsForDeletingNames.add("req3");

        ChangeVacancyDtoRequest changeVacancyDtoRequest = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 0,"newVacancyName", 2, newRequirementsForAdding, requirementsForDeletingNames
        );
        server.changeVacancy(gson.toJson(changeVacancyDtoRequest));
        String responseJson = server.getVacancies(gson.toJson(DtoRequestsFactory.makeGetVacanciesDtoRequest(token)));
        List<Vacancy> receivedVacanciesList = gson.fromJson(responseJson, VacanciesListDtoResponse.class).getVacancies();

        assertEquals(1, receivedVacanciesList.size());
        Vacancy actualVacancy = receivedVacanciesList.get(0);
        assertEquals(changeVacancyDtoRequest.getNewVacancyName(), actualVacancy.getVacancyName());
        assertEquals(changeVacancyDtoRequest.getNewPayment(), (Integer)actualVacancy.getPayment());

        RequirementsList expectedRequirements = requirements;
        for(String requirementName: changeVacancyDtoRequest.getRequirementsForDeletingNames())
            expectedRequirements.deleteRequirement(requirementName);
        expectedRequirements.addRequirements(changeVacancyDtoRequest.getNewOrChangedRequirements());
        RequirementsList actualRequirements = actualVacancy.getRequirements();

        assertEquals(expectedRequirements.size(), actualRequirements.size());
        for(String requirementName: expectedRequirements.getRequirementsNamesSet()) {
            assertEquals(expectedRequirements.getRequirementProperties(requirementName).getLvl(), actualRequirements.getRequirementProperties(requirementName).getLvl());
            assertEquals(expectedRequirements.getRequirementProperties(requirementName).isNecessary(), actualRequirements.getRequirementProperties(requirementName).isNecessary());
        }

        ChangeVacancyDtoRequest changeVacancyDtoRequestOnlyPayment = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 0, null, 3, null, null
        );
        server.changeVacancy(gson.toJson(changeVacancyDtoRequestOnlyPayment));
        responseJson = server.getVacancies(gson.toJson(DtoRequestsFactory.makeGetVacanciesDtoRequest(token)));
        actualVacancy = gson.fromJson(responseJson, VacanciesListDtoResponse.class).getVacancies().get(0);

        assertEquals(changeVacancyDtoRequestOnlyPayment.getNewPayment(), (Integer)actualVacancy.getPayment());

        server.stopServer("");
    }

    @Test
    public void invalidVacancyChanging() {
        Server server = new Server();
        server.startServer("");
        Gson gson = new Gson();

        UUID token = gson.fromJson(server.registerEmployer(
                gson.toJson(SpecialDtoRequestsFactory.makeValidRegisterEmployerDtoRequest())), SuccessfulRegisteredDtoResponse.class).getToken();
        RequirementsList requirementsList = new RequirementsList();
        requirementsList.addRequirement("req1", new RequirementProperties(1, false));
        requirementsList.addRequirement("req2", new RequirementProperties(3, true));
        AddVacancyDtoRequest addVacancyDtoRequest = DtoRequestsFactory.makeAddVacancyDtoRequest(
                token, "vacancy1", 1, new RequirementsList()
        );
        server.addVacancy(gson.toJson(addVacancyDtoRequest));

        ChangeVacancyDtoRequest changeNonexistentVacancyDtoRequest = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 1, "vacancy2", null, null, null
        );
        ChangeVacancyDtoRequest changeVacancyDtoRequestWithInvalidVacancyNumber = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, -1, "vacancy2", null, null, null
        );
        ChangeVacancyDtoRequest changeVacancyDtoRequestWithNullVacancyNumber = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, null, "vacancy2", null, null, null
        );
        ChangeVacancyDtoRequest changeVacancyDtoRequestWithInvalidNewVacancyName = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 0, "", null, null, null
        );
        ChangeVacancyDtoRequest changeVacancyDtoRequestWithInvalidNewPayment = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 0, null, -1, null, null
        );
        Set<String> nonexistentRequirementsForDeletingNames = new HashSet<>();
        nonexistentRequirementsForDeletingNames.add("nonexistentReq");
        ChangeVacancyDtoRequest changeVacancyDtoRequestWithNonexistentRequirementsForDeletingNames = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 0, null, null, null, nonexistentRequirementsForDeletingNames
        );
        RequirementsList requirementsWithInvalidNames = new RequirementsList();
        requirementsWithInvalidNames.addRequirement("", new RequirementProperties(1, false));
        ChangeVacancyDtoRequest changeVacancyDtoRequestWithInvalidRequirementsForAddingNames = DtoRequestsFactory.makeChangeVacancyDtoRequest(
                token, 0, null, null, requirementsWithInvalidNames, null
        );

        ChangeVacancyDtoRequest invalidRequests[] = {
                changeNonexistentVacancyDtoRequest, changeVacancyDtoRequestWithInvalidVacancyNumber, changeVacancyDtoRequestWithNullVacancyNumber, changeVacancyDtoRequestWithInvalidNewVacancyName,
                changeVacancyDtoRequestWithInvalidNewPayment, changeVacancyDtoRequestWithNonexistentRequirementsForDeletingNames,
                changeVacancyDtoRequestWithInvalidRequirementsForAddingNames
        };

        for(ChangeVacancyDtoRequest invalidRequest: invalidRequests) {
            String responseJson = server.changeVacancy(gson.toJson(invalidRequest));
            assertNotNull(gson.fromJson(responseJson, ErrorDtoResponse.class).getError());
        }

        server.stopServer("");
    }
}
