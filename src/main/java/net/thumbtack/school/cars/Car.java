package net.thumbtack.school.cars;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorErrorCode;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;


public class Car implements Colored {
    private Color color;
    private int weight, maxSpeed;
    private String model;

    public Car (String model, int weight, int maxSpeed, Color color) throws ColorException {
        this.model = model;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        if(color == null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    public Car (String model, int weight, int maxSpeed, String colorString) throws ColorException {
        this.model = model;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.color = Color.colorFromString(colorString);

    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) throws ColorException {
        if(color == null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        this.color = color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        this.color = Color.colorFromString(colorString);
    }
}
