package com.Chaos.utils;

import com.Chaos.dataobjects.VertexMemory;

import java.util.function.Predicate;

/**
 * Created by PalaniappanKumarappan on 5/16/17.
 */
public final class FractalPredicates {

    public static Predicate<VertexMemory> notSameAsBefore(){
        return v -> v.getCurrentVertex()==v.getPreviousVertex();
    }

    public static Predicate<VertexMemory> notAlternative(){
        return v -> Math.abs(v.getCurrentVertex()-v.getPreviousVertex())==2;
    }

    public static Predicate<VertexMemory> oneSkipAntiClock(){
        return v -> ((v.getCurrentVertex()==1 && v.getPreviousVertex()==4) || v.getCurrentVertex()-v.getPreviousVertex()==1) ;
    }

    public static Predicate<VertexMemory> theBush(){
        return v -> (Math.abs(v.getCurrentVertex()-v.getPreviousVertex())==2
                && Math.abs(v.getCurrentVertex()-v.getSecondLastVertex())==2) ;
    }

    public static Predicate<VertexMemory> noNeighbors(){
        return v -> (Math.abs(v.getCurrentVertex()-v.getPreviousVertex())==1
                && Math.abs(v.getCurrentVertex()-v.getSecondLastVertex())==3) ;
    }

    public static Predicate<VertexMemory> noNeighborsPent(){
        return v -> (Math.abs(v.getCurrentVertex()-v.getPreviousVertex())==1
                && Math.abs(v.getCurrentVertex()-v.getPreviousVertex())==4) && (Math.abs(v.getCurrentVertex()-v.getSecondLastVertex())==1
                && Math.abs(v.getCurrentVertex()-v.getSecondLastVertex())==4) ;
    }


}
