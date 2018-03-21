package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    Queue<Trainee> traineeQueue;

    public TraineeQueue() {
        traineeQueue = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee) {
        traineeQueue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {
        if(traineeQueue.size() == 0)
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        return traineeQueue.remove();
    }
}
