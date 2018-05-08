package net.thumbtack.school.hiring.data.models;

import java.util.*;

public class RequirementsList {
    private Map<String, RequirementProperties> requirements;

    private RequirementsList(Map<String, RequirementProperties> requirements) {
        this.requirements = requirements;
    }

    public RequirementsList() {
        this(new HashMap<>());
    }

    public void addRequirement(String requirementName, RequirementProperties requirement) {
        requirements.put(requirementName, requirement);
    }

    public RequirementProperties getRequirementProperties(String requirementName) {
        return requirements.get(requirementName);
    }

    public int getRequirementLvl(String requirementName) {
        return getRequirementProperties(requirementName).getLvl();
    }

    public boolean isRequirementNecessary(String requirementName) {
        return getRequirementProperties(requirementName).isNecessary();
    }

    public Set<String> getRequirementsNamesSet() {
        return requirements.keySet();
    }

    public int size() {
        return requirements.size();
    }

    public void addRequirements(RequirementsList newRequirements) {
        for(String newRequirementName: newRequirements.getRequirementsNamesSet()) {
            addRequirement(newRequirementName, newRequirements.getRequirementProperties(newRequirementName));
        }
    }

    public void deleteRequirement(String requirementName) {
        requirements.remove(requirementName);
    }

    public boolean existsRequirement(String requirementName) {
        return requirements.containsKey(requirementName);
    }

    public Map<String, RequirementProperties> toMap() {
        return requirements;
    }

    public static RequirementsList fromMap(Map<String, RequirementProperties> requirements) {
        return new RequirementsList(requirements);
    }
}
