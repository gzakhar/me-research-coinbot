import domain.Trades;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import services.Connector;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Application {


    MySwing mySwingWorker;
    SwingWrapper<XYChart> sw;
    XYChart chart;

    public static void main(String[] args) throws Exception {
        Application swingRealTime = new Application();
        swingRealTime.go();

        /*  TEST */
//        /********************************/
//
//        String URL = "https://api.bittrex.com/v3/markets/BTC-USDT/trades";
//
//        SwingWrapper<XYChart> s;
//
//        XYChart chart2;
//
//
//        LinkedList<Double> fi = new LinkedList<>();
//        fi.add(0.0);
//
//        chart2 = QuickChart.getChart("SwingWorker XChart Real-time Demo", "Time", "Value", "price2", new double[]{0}, new double[]{0});
//        chart2.getStyler().setLegendVisible(false);
//        chart2.getStyler().setXAxisTicksVisible(false);
//
//        s = new SwingWrapper<>(chart2);
//        s.displayChart();
//
//
//        while (true) {
//            double rate = Connector.executeGet(URL).getRate();
//
//            fi.add(rate);
//
//            chart2.updateXYSeries("price2", null, fi.stream().mapToDouble(Double::doubleValue).toArray(), null);
//
//
//            TimeUnit.SECONDS.sleep(5);
//        }
//
//
//        /********************************/


    }


    private void go() {

        // Create Chart
        chart = QuickChart.getChart("SwingWorker XChart Real-time Demo", "Time", "Value", "price", new double[]{0}, new double[]{0});
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);


        // Show it
        sw = new SwingWrapper<>(chart);
        sw.displayChart();


        mySwingWorker = new MySwing();
        mySwingWorker.execute();
    }


    private class MySwing extends SwingWorker<Boolean, double[]> {
        LinkedList<Double> fifo2 = new LinkedList<Double>();


        MySwing() {

            fifo2.add(11800.0);
        }


        @Override
        protected Boolean doInBackground() throws Exception {
            while (!isCancelled()) {

                Trades res = Connector.executeGet("https://api.bittrex.com/v3/markets/BTC-USDT/trades");
                fifo2.add(res.getRate());
                if (fifo2.size() > 200) {

                    fifo2.removeFirst();
                }


                publish(fifo2.stream().mapToDouble(Double::doubleValue).toArray());
                TimeUnit.SECONDS.sleep(10);
            }

            return true;
        }


        @Override
        protected void process(List<double[]> chunks) {
            System.out.print(chunks.size());

            chart.updateXYSeries("price", null, chunks.get(chunks.size() - 1), null);
            sw.repaintChart();

        }


    }


}
