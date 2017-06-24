package com.Chaos;

import com.Chaos.dataobjects.*;
import com.Chaos.service.ChaosAndOrder;
import org.knowm.xchart.*;

import java.util.ArrayList;
import java.util.List;

import static com.Chaos.utils.LoadFractals.getFractal;

/**
 * Creates a simple real-time chart
 */
public class SimpleRealTime {

    public static void main(String[] args) throws Exception {
        points = new ArrayList<>();
        double[][] initdata = getChaosData();

        // Create Chart
        final XYChart chart = new XYChartBuilder().width(1200).height(900).build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setPlotBorderVisible(false);
        chart.getStyler().setMarkerSize(1);
        chart.addSeries("chaos", initdata[0], initdata[1]);
        final SwingWrapper<XYChart> sw = new SwingWrapper<>(chart);
        sw.displayChart();

        int n=7000;
        while (n<350000) {
            n+=7000;
            Thread.sleep(900);
            final double[][] data = getChaosData();
            chart.updateXYSeries("chaos", data[0], data[1], null);
            sw.repaintChart();
        }
    }

    static List<com.Chaos.dataobjects.Point> points;
    private static double[][] getChaosData() {

        Fractal fractal = getFractal(FractalType.TRIANGLE);
        ChaosAndOrder cao = new ChaosAndOrder(fractal);
        points.addAll(cao.generateMovements());
        double[] xData = new double[points.size()];
        double[] yData = new double[points.size()];
        for(int i=0;i<points.size();i++){
            com.Chaos.dataobjects.Point point = points.get(i);
            xData[i] = point.getX();
            yData[i] = point.getY();
        }
        return new double[][] { xData, yData };
    }
}
