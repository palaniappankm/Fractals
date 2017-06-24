package com.Chaos;

import com.Chaos.dataobjects.Point;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PalaniappanKumarappan on 5/11/17.
 */
public class ChaosAndOrder_baseline {

    Map<Integer, Point> vertices = new HashMap<>();

    List<Point> points = new ArrayList<>();

    Point currentPoint;

    double moveRatio;

    public static final int FREQUENCY = 20000;

    public static final int DELAY = 400;

    public static final int DEPTH = 3000000;


    public ChaosAndOrder_baseline(int corners){
        if(corners==3) {
            vertices.put(1, new Point(0, 5));
            vertices.put(2, new Point(5, 5));
            vertices.put(3, new Point(2.5, 2.5));
            moveRatio = 0.60;
        } else if(corners==4){
            vertices.put(1, new Point(0, 0));
            vertices.put(2, new Point(0, 5));
            vertices.put(3, new Point(5, 5));
            vertices.put(4, new Point(5, 0));
            moveRatio = 0.55;
        } else if(corners==5){
            vertices.put(1, new Point(5.5, 4.5));
            vertices.put(2, new Point(4.55, 5.19));
            vertices.put(3, new Point(4.91, 6.31));
            vertices.put(4, new Point(6.09, 6.31));
            vertices.put(5, new Point(6.45, 5.19));
            moveRatio = 0.6250;
        } else if(corners==6){
            vertices.put(1, new Point(-3, -5.196));
            vertices.put(2, new Point(-6, 0));
            vertices.put(3, new Point(-3, 5.196));
            vertices.put(4, new Point(3, 5.196));
            vertices.put(5, new Point(6, 0));
            vertices.put(6, new Point(3, -5.196));
            moveRatio = 0.66666666667;
        } else if(corners==8){
            vertices.put(1, new Point(0, 0));
            vertices.put(2, new Point(2, 0));
            vertices.put(3, new Point(4, 0));
            vertices.put(4, new Point(4, 2));
            vertices.put(5, new Point(4, 4));
            vertices.put(6, new Point(2, 4));
            vertices.put(7, new Point(0, 4));
            vertices.put(8, new Point(0, 2));
            moveRatio = 0.66666666667;
        }
        currentPoint = new Point(7.5, 2.5);
        points.add(currentPoint);
    }

    public int getRandomNum(int vertices){
        int randomNum = (int) (((Math.random()*Math.random()*Math.random()*1973579) % vertices + 1));

        return randomNum;
    }

    public List<Point> generateMovements(){
        int n=1;
        List<Point> points = new ArrayList<>();
        while(n<=FREQUENCY) {
            n++;
            int move = getRandomNum(vertices.size());
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
        double x = currentPoint.getX() + (moveTowards.getX()-currentPoint.getX())*moveRatio;
        double y = currentPoint.getY() + (moveTowards.getY()-currentPoint.getY())*moveRatio;
        return new Point(x, y);
    }

    public static void main(String[] args) throws Exception {
        ChaosAndOrder_baseline cao = new ChaosAndOrder_baseline(8);

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
