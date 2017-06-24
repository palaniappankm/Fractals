package com.Chaos.utils;

import com.Chaos.dataobjects.Fractal;
import com.Chaos.dataobjects.FractalType;
import com.Chaos.dataobjects.Point;
import com.Chaos.dataobjects.VertexMemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Created by PalaniappanKumarappan on 5/15/17.
 */
public final class LoadFractals {

    public static  Map<FractalType, Fractal> FRACTALS = new HashMap<>();

    public static Predicate<VertexMemory> defaultPredicate() {
        return (v)-> false;
    }

    static{
        /*Constructing 3 Vertex fractal - Triangle*/
        Map<Integer, Point> triVertices = new HashMap<>();
        triVertices.put(1, new Point(0, 0));
        triVertices.put(2, new Point(5, 5));
        triVertices.put(3, new Point(10, 0));
        Fractal simpleTriangle = new Fractal.FractalBuilder()
                                            .withNumberOfVertices(3)
                                            .withCompression(0.50)
                                            .withVertices(triVertices)
                                            .withPredicate(defaultPredicate())
                                            .build();
        FRACTALS.put(FractalType.TRIANGLE, simpleTriangle);

        /*Constructing 4 Vertex fractal - Square*/
        Map<Integer, Point> quadVertices = new HashMap<>();
        quadVertices.put(1, new Point(0, 0));
        quadVertices.put(2, new Point(0, 5));
        quadVertices.put(3, new Point(5, 5));
        quadVertices.put(4, new Point(5, 0));
        Fractal squareFractal = new Fractal.FractalBuilder()
                .withNumberOfVertices(4)
                .withCompression(0.60)
                .withVertices(quadVertices)
                .withPredicate(defaultPredicate())
                .build();
        FRACTALS.put(FractalType.SQUARE, squareFractal);

        /*Constructing 5 Vertex fractal - Pentagon*/
        Map<Integer, Point> pentVertices = new HashMap<>();
        pentVertices.put(1, new Point(5.5, 4.5));
        pentVertices.put(2, new Point(4.55, 5.19));
        pentVertices.put(3, new Point(4.91, 6.31));
        pentVertices.put(4, new Point(6.09, 6.31));
        pentVertices.put(5, new Point(6.45, 5.19));
        Fractal pentagon = new Fractal.FractalBuilder()
                .withNumberOfVertices(5)
                .withCompression(0.6250)
                .withVertices(pentVertices)
                .withPredicate(defaultPredicate())
                .build();
        FRACTALS.put(FractalType.PENTAGON, pentagon);

        /*Constructing 6 Vertex fractal - Hexagon*/
        Map<Integer, Point> hexVertices = new HashMap<>();
        hexVertices.put(1, new Point(-3, -5.196));
        hexVertices.put(2, new Point(-6, 0));
        hexVertices.put(3, new Point(-3, 5.196));
        hexVertices.put(4, new Point(3, 5.196));
        hexVertices.put(5, new Point(6, 0));
        hexVertices.put(6, new Point(3, -5.196));
        Fractal hexagon = new Fractal.FractalBuilder()
                .withNumberOfVertices(6)
                .withCompression(0.66666666667)
                .withVertices(hexVertices)
                .withPredicate(defaultPredicate())
                .build();
        FRACTALS.put(FractalType.HEXAGON, hexagon);

        /*Constructing 6 Vertex fractal - Hexagon*/
        Map<Integer, Point> carpetVertices = new HashMap<>();
        carpetVertices.put(1, new Point(0, 0));
        carpetVertices.put(2, new Point(2, 0));
        carpetVertices.put(3, new Point(4, 0));
        carpetVertices.put(4, new Point(4, 2));
        carpetVertices.put(5, new Point(4, 4));
        carpetVertices.put(6, new Point(2, 4));
        carpetVertices.put(7, new Point(0, 4));
        carpetVertices.put(8, new Point(0, 2));
        Fractal carpet = new Fractal.FractalBuilder()
                .withNumberOfVertices(8)
                .withCompression(0.66666666667)
                .withVertices(carpetVertices)
                .withPredicate(defaultPredicate())
                .build();
        FRACTALS.put(FractalType.CARPET, carpet);
    }

    public static Fractal getFractal(FractalType type){
        return FRACTALS.get(type);
    }
}
