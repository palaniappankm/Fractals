package com.Chaos;

import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartFrame extends JFrame {
    private static final int MAX_ITERATION = 100000000;
    private static final int FPS = 25;
    private final int REFRESH_RATE = 1000/FPS;
    private XYSeries minFitSerie;
    private XYSeries avgFitSerie;
    private XYSeries maxFitSerie;

    private int N = 50;
    private double[] minFitVal = new double[N];
    private double[] maxFitVal = new double[N];
    private double[] avgFitVal = new double[N];

    private long lastTime;

    private Random rand = new Random();

    public ChartFrame() {
        super("Chart");
        setSize(1024, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(makeChart());
        setVisible(true);
        lastTime = System.nanoTime();

        //run();
    }

    public void run() {
        for(int i = 0; i < MAX_ITERATION; i++) {
            //alg.runIteration(); here complex calculations
            generateSeries();
            long currentTime = System.nanoTime();
            long elapsed = currentTime - lastTime;
            elapsed /= 1000000;
            if(elapsed < REFRESH_RATE) continue;
            lastTime = currentTime;
            //repaint();
        }
    }

    private void generateSeries() {
        minFitSerie.clear();
        maxFitSerie.clear();
        avgFitSerie.clear();

        for(int i = 0; i < N - 1; i++) {
            minFitVal[i] = minFitVal[i+1];
            maxFitVal[i] = maxFitVal[i+1];
            avgFitVal[i] = avgFitVal[i+1];
            minFitSerie.add(i, minFitVal[i]);
            maxFitSerie.add(i, maxFitVal[i]);
            avgFitSerie.add(i, avgFitVal[i]);
        }
        int i = N - 1;
        double v1 = rand.nextDouble()*10 + 180;
        double v2 = rand.nextDouble()*10 + 190;
        double v3 = (v1 + v2)/2;
        minFitVal[i] = v1;
        maxFitVal[i] = v2;
        avgFitVal[i] = v3;
        minFitSerie.add(i, minFitVal[i]);
        maxFitSerie.add(i, maxFitVal[i]);
        avgFitSerie.add(i, avgFitVal[i]);
    }

    ChartPanel makeChart() {
        minFitSerie = new XYSeries("min");
        maxFitSerie = new XYSeries("max");
        avgFitSerie = new XYSeries("avg");
        XYDataset xyDatasetMin = new XYSeriesCollection(minFitSerie);
        XYDataset xyDatasetMax = new XYSeriesCollection(maxFitSerie);
        XYDataset xyDatasetAvg = new XYSeriesCollection(avgFitSerie);

        JFreeChart chart = ChartFactory.createXYLineChart("Population overview", "Population number", "Fitness",
                null, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundImageAlpha(0.1f);
        ((NumberAxis)chart.getXYPlot().getDomainAxis()).setRange(0, 500);
        ((NumberAxis)chart.getXYPlot().getRangeAxis()).setAutoRangeIncludesZero(false);
        ((NumberAxis)chart.getXYPlot().getRangeAxis()).setRange(180,200);
        XYPlot plot = chart.getXYPlot();


        SimpleXYDataset dataset = new SimpleXYDataset();
        plot.setDataset(0, dataset);
        plot.setRenderer(0, new XYLineAndShapeRenderer(true, false));
        //plot.setDataset(1, xyDatasetMax);
        //plot.setRenderer(1, new XYLineAndShapeRenderer(true, false));
        //plot.setDataset(2, xyDatasetAvg);
        //plot.setRenderer(2, new XYLineAndShapeRenderer(true, false));
        new Thread(dataset).start();
        return new ChartPanel(chart);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ChartFrame frame = new ChartFrame();
    }

}
class SimpleXYDataset extends AbstractXYDataset implements Runnable{
    private final int N = 500;
    private double[][] data;
    private final int REPETITIONS = 10;
    private Random rand = new Random();

    SimpleXYDataset(){
        super();
        data = new double[4][N];
        Arrays.fill(data[0], Double.NaN);
        Arrays.fill(data[1], Double.NaN);
        Arrays.fill(data[2], Double.NaN);
        Arrays.fill(data[3], Double.NaN);

    }
    public int getSeriesCount(){
        return 3;
    }
    public Comparable getSeriesKey(int series){
        return "Series " + series;
    }
    public Number getX(int series, int item){
        return new Double(data[0][item]);
    }
    public Number getY(int series, int item){
        return new Double(data[1 + series][item]);
    }
    public int getItemCount(int series){
        return N;
    }
    public void run(){
        for(int r = 0; r < REPETITIONS; r++){
            for(int i = 0; i < N; i++){
                data[0][i] = i;
                double v1 = rand.nextDouble()*10 + 180;
                double v2 = rand.nextDouble()*10 + 190;
                double v3 = (v1 + v2)/2;
                data[1][i] = v1;
                data[2][i] = v2;
                data[3][i] = v3;
                fireDatasetChanged();
                try{
                    Thread.sleep(25);
                }
                catch(InterruptedException e){
                }
            }
            double[][] newData = new double[4][N];
            Arrays.fill(newData[0], Double.NaN);
            Arrays.fill(newData[1], Double.NaN);
            Arrays.fill(newData[2], Double.NaN);
            Arrays.fill(newData[3], Double.NaN);
            data = newData;
            fireDatasetChanged();
        }
    }
}

