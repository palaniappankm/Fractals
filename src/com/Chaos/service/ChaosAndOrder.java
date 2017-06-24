package com.Chaos.service;

import com.Chaos.dataobjects.Fractal;
import com.Chaos.dataobjects.FractalType;
import com.Chaos.dataobjects.Point;
import com.Chaos.dataobjects.VertexMemory;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import static com.Chaos.utils.FractalPredicates.notSameAsBefore;
import static com.Chaos.utils.FractalPredicates.*;
import static com.Chaos.utils.LoadFractals.*;

/**
 * Created by PalaniappanKumarappan on 5/11/17.
 */
public class ChaosAndOrder {

    List<Point> points = new ArrayList<>();

    Point currentPoint;

    Fractal fractal;

    VertexMemory vertexMemory;

    public static final int FREQUENCY = 60000;

    public static final int DELAY = 1000;

    public static final int DEPTH = 2000000;

    public  ChaosAndOrder(Fractal fractal){
        this.fractal = fractal;
        this.currentPoint = new Point(7.5, 2.5);
        this.points.add(currentPoint);
        vertexMemory = new VertexMemory.Builder()
                .withCurrentVertex(-1)
                .withPreviousVertex(-1)
                .withSecondLastVertex(-1)
                .build();

    }

    public int getRandomNum(int vertices){
        int randomNum = (int) (((Math.random()*Math.random()*Math.random()*3189759) % vertices + 1));
        updateVertexMemory(randomNum);
        while(fractal.getPredicate().test(vertexMemory)){
            randomNum = (int) (((Math.random()*Math.random()*Math.random()*3189759) % vertices + 1));
            updateVertexMemory(randomNum);
        }
        return randomNum;
    }

    private VertexMemory updateVertexMemory(int randomNum) {
        vertexMemory = new VertexMemory.Builder()
                .withCurrentVertex(randomNum)
                .withPreviousVertex(vertexMemory.getCurrentVertex())
                .withSecondLastVertex(vertexMemory.getPreviousVertex())
                .build();
        return vertexMemory;
    }

    public List<Point> generateMovements(){
        int noOfVertices = fractal.getNumberOfVertices();
        Map<Integer, Point> vertices = fractal.getVertices();
        int n=1;
        List<Point> points = new ArrayList<>();
        while(n<=FREQUENCY) {
            n++;
            int move = getRandomNum(noOfVertices);
            Point moveTowards = vertices.get(move);
            currentPoint = getMovePoint(currentPoint, moveTowards);
            points.add(currentPoint);
        }
        return points;
    }

    private double[][] getChaosData(int n) {
        points.addAll(generateMovements());
        double[] xData = new double[n];
        double[] yData = new double[n];
        for(int i=1;i<n;i++){
            Point point = points.get(i);
            xData[i] = point.getX();
            yData[i] = point.getY();
        }
        return new double[][] { xData, yData };
    }

    private Point getMovePoint(Point currentPoint, Point moveTowards) {
        double compression = fractal.getCompression();
        double x = currentPoint.getX() + (moveTowards.getX()-currentPoint.getX())*compression;
        double y = currentPoint.getY() + (moveTowards.getY()-currentPoint.getY())*compression;
        return new Point(x, y);
    }

    public static void main(String[] args) throws Exception {


        Fractal fractal = new Fractal.FractalBuilder(getFractal(FractalType.SQUARE))
                .withCompression(0.5)
                .withPredicate(notSameAsBefore())
                .build();
        //Fractal fractal = getFractal(FractalType.SQUARE);
        ChaosAndOrder cao = new ChaosAndOrder(fractal);

        // Create Chart
        final XYChart chart = new XYChartBuilder().width(1200).height(900).build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setMarkerSize(1);

        int n=FREQUENCY;
        double[][] initdata = cao.getChaosData(n);
        chart.addSeries("chaos", initdata[0], initdata[1]);
        final SwingWrapper<XYChart> sw = new SwingWrapper<>(chart);
        sw.displayChart();

        while (n<DEPTH) {
            n+=FREQUENCY;
            Thread.sleep(DELAY);
            System.out.println(n);
            final double[][] data = cao.getChaosData(n);
            chart.updateXYSeries("chaos", data[0], data[1], null);
            sw.repaintChart();
        }
    }

}
