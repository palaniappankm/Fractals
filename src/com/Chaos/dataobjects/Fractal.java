package com.Chaos.dataobjects;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by PalaniappanKumarappan on 5/15/17.
 */
public class Fractal {

    private int numberOfVertices;

    private Map<Integer, Point> vertices;

    private double compression;

    private Rotation rotation;

    private Predicate<VertexMemory> predicate;

    private Fractal(FractalBuilder fractalBuilder) {
        this.numberOfVertices = fractalBuilder.numberOfVertices;
        this.vertices = fractalBuilder.vertices;
        this.compression = fractalBuilder.compression;
        this.rotation = fractalBuilder.rotation;
        this.predicate = fractalBuilder.predicate;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public Map<Integer, Point> getVertices() {
        return vertices;
    }

    public double getCompression() {
        return compression;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Predicate<VertexMemory> getPredicate() {
        return predicate;
    }

    public static final class FractalBuilder {
        private int numberOfVertices;
        private Map<Integer, Point> vertices;
        private double compression;
        private Rotation rotation;
        private Predicate<VertexMemory> predicate;


        public FractalBuilder(Fractal fractal){
            this.numberOfVertices = fractal.getNumberOfVertices();
            this.compression = fractal.getCompression();
            this.rotation = fractal.getRotation();
            this.vertices = fractal.getVertices();
            this.predicate = fractal.getPredicate();
        }

        public FractalBuilder(){

        }

        public Fractal build() {
            return new Fractal(this);
        }

        public FractalBuilder withNumberOfVertices(int numberOfVertices) {
            this.numberOfVertices = numberOfVertices;
            return this;
        }

        public FractalBuilder withVertices(Map<Integer, Point> points) {
            this.vertices = points;
            return this;
        }

        public FractalBuilder withCompression(double compression) {
            this.compression = compression;
            return this;
        }

        public FractalBuilder withRotation(Rotation rotation) {
            this.rotation = rotation;
            return this;
        }

        public FractalBuilder withPredicate(Predicate<VertexMemory> predicate) {
            this.predicate = predicate;
            return this;
        }
    }
}
