package com.Chaos.dataobjects;

/**
 * Created by PalaniappanKumarappan on 5/16/17.
 */
public class VertexMemory {

    private int currentVertex;

    private int previousVertex;

    private int secondLastVertex;

    private VertexMemory(Builder builder) {
        this.currentVertex = builder.currentVertex;
        this.previousVertex = builder.previousVertex;
        this.secondLastVertex = builder.secondLastVertex;
    }

    public static Builder newVertexMemory() {
        return new Builder();
    }

    public int getCurrentVertex() {
        return currentVertex;
    }

    public int getPreviousVertex() {
        return previousVertex;
    }

    public int getSecondLastVertex() {
        return secondLastVertex;
    }


    public static final class Builder {
        private int currentVertex;
        private int previousVertex;
        private int secondLastVertex;

        public Builder() {

        }

        public VertexMemory build() {
            return new VertexMemory(this);
        }

        public Builder withCurrentVertex(int currentVertex) {
            this.currentVertex = currentVertex;
            return this;
        }

        public Builder withPreviousVertex(int previousVertex) {
            this.previousVertex = previousVertex;
            return this;
        }

        public Builder withSecondLastVertex(int secondLastVertex) {
            this.secondLastVertex = secondLastVertex;
            return this;
        }
    }
}
