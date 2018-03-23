package net.thumbtack.school.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private Map<Trainee, String> traineesInfo;

    public TraineeMap() {
        traineesInfo = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(traineesInfo.containsKey(trainee))
            throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        traineesInfo.put(trainee, institute);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        if(traineesInfo.containsKey(trainee))
            traineesInfo.put(trainee, institute);
        else
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        if(traineesInfo.containsKey(trainee))
            traineesInfo.remove(trainee);
        else
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public int getTraineesCount() {
        return traineesInfo.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        String institute = traineesInfo.get(trainee);
        if(institute == null)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return institute;
    }

    public Set<Trainee> getAllTrainees() {
        return traineesInfo.keySet();
    }

    public Set<String> getAllInstitutes() {
        return new HashSet<>(traineesInfo.values());
    }

    public boolean isAnyFromInstitute(String institute) {
        return traineesInfo.values().contains(institute);
    }
}
