package services;


import domain.Trades;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Chart {

    MySwingWorker mySwingWorker;
    SwingWrapper<XYChart> sw;
    XYChart chart;


    public static void main(String[] args) throws Exception {

        Chart swingWorkerRealTime = new Chart();
        swingWorkerRealTime.go();


    }


    private void go() {

        // Create Chart
        chart = QuickChart.getChart("SwingWorker XChart Real-time Demo", "Time", "Value", "randomWalk", new double[]{0}, new double[]{0});
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);


        // Show it
        sw = new SwingWrapper<>(chart);
        sw.displayChart();


        mySwingWorker = new MySwingWorker();
        mySwingWorker.execute();
    }


    private class MySwingWorker extends SwingWorker<Boolean, double[]> {

        LinkedList<Double> fifo = new LinkedList<>();

        public MySwingWorker() {

            fifo.add(11599.0);
        }


        @Override
        protected Boolean doInBackground() throws Exception {

            while (!isCancelled()) {
                Trades sum = Connector.executeGet("https://api.bittrex.com/v3/markets/BTC-USDT/trades");
                fifo.add(sum.getRate());
                if (fifo.size() > 200) {
                    fifo.removeFirst();
                }

                double[] array = new double[fifo.size()];
                for (int i = 0; i < fifo.size(); i++) {
                    array[i] = fifo.get(i);
                }
                publish(array);

                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    // eat it. caught when interrupt is called
                    System.out.println("MySwingWorker shut down.");
                }

            }

            return true;
        }


        @Override
        protected void process(List<double[]> chunks) {

            System.out.println("number of chunks: " + chunks.size());

            double[] mostRecentDataSet = chunks.get(chunks.size() - 1);

            chart.updateXYSeries("randomWalk", null, mostRecentDataSet, null);
            sw.repaintChart();

            long start = System.currentTimeMillis();
            long duration = System.currentTimeMillis() - start;
            try {
                Thread.sleep(40 - duration);
            } catch (InterruptedException e) {
            }

        }


    }
}





































