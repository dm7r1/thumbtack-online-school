package net.thumbtack.school.ttschool;

import net.thumbtack.school.boxes.ArrayBox;

import java.util.*;

public class Group {
    private String name, room;
    private List<Trainee> trainees;

    public Group(String name, String room) throws TrainingException {
        this.setName(name);
        this.setRoom(room);
        this.trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if(name == null || name.length() == 0)
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if(room == null || room.length() == 0)
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void  addTrainee(Trainee trainee) {
        this.trainees.add(trainee);
    }

    public void  removeTrainee(Trainee trainee) throws TrainingException {
        if(trainees.contains(trainee))
            trainees.remove(trainee);
        else
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  removeTrainee(int index) throws TrainingException {
        if (index < 0 || trainees.size() <= index)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        trainees.remove(index);
    }

    public Trainee  getTraineeByFirstName(String firstName) throws TrainingException {
        for(Trainee trainee: trainees)
            if(trainee.getFirstName().equals(firstName))
                return trainee;
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee  getTraineeByFullName(String fullName) throws TrainingException {
        for(Trainee trainee: trainees)
            if(trainee.getFullName().equals(fullName))
                return trainee;
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  sortTraineeListByFirstNameAscendant() {
        trainees.sort(Comparator.comparing(Trainee::getFirstName));
    }

    public void  sortTraineeListByRatingDescendant() {
        trainees.sort((a, b) -> -Integer.compare(a.getRating(), b.getRating()));
    }

    public void  reverseTraineeList() {
        Collections.reverse(trainees);
    }

    public void  rotateTraineeList(int positions) {
        Collections.rotate(trainees, positions);
    }

    public List<Trainee>  getTraineesWithMaxRating() throws TrainingException {
        if(trainees.size() == 0)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        int max = Collections.max(trainees, Comparator.comparing(Trainee::getRating)).getRating();
        List<Trainee> traineesWithMaxRating = new LinkedList<>();
        for (Trainee trainee: trainees)
            if (trainee.getRating() == max)
                traineesWithMaxRating.add(trainee);
        return traineesWithMaxRating;
    }

    public boolean  hasDuplicates() {
        Set traineesSet = new HashSet<>(trainees);
        return traineesSet.size() != trainees.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(name, group.name) &&
                Objects.equals(room, group.room) &&
                Objects.equals(trainees, group.trainees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, room, trainees);
    }
}
