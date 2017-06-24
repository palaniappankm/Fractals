package com.Chaos.dataobjects;

/**
 * Created by PalaniappanKumarappan on 5/15/17.
 */
public class Rotation {

    private double rotationAngle;

    private int rotationVertex;

    private boolean isAntiClockWise;

    private Rotation(RotationBuilder rotationBuilder) {
        this.rotationAngle = rotationBuilder.rotationAngle;
        this.rotationVertex = rotationBuilder.rotationVertex;
        this.isAntiClockWise = rotationBuilder.isAntiClockWise;
    }

    public static RotationBuilder newRotation() {
        return new RotationBuilder();
    }


    public double getRotationAngle() {
        return rotationAngle;
    }

    public int getRotationVertex() {
        return rotationVertex;
    }

    public boolean isAntiClockWise() {
        return isAntiClockWise;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "rotated at an Angle of=" + rotationAngle +
                ", on Vertex number=" + rotationVertex +
                ", with isAntiClockWise=" + isAntiClockWise +
                '}';
    }

    public static final class RotationBuilder {
        private double rotationAngle;
        private int rotationVertex;
        private boolean isAntiClockWise;

        private RotationBuilder() {
        }

        public Rotation build() {
            return new Rotation(this);
        }

        public RotationBuilder withRotationAngle(double rotationAngle) {
            this.rotationAngle = rotationAngle;
            return this;
        }

        public RotationBuilder withRotationVertex(int rotationVertex) {
            this.rotationVertex = rotationVertex;
            return this;
        }

        public RotationBuilder isAntiClockWise(boolean isAntiClockWise) {
            this.isAntiClockWise = isAntiClockWise;
            return this;
        }
    }
}
