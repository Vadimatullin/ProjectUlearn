import java.awt.Color;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ui.ApplicationFrame;

public class Graph extends ApplicationFrame {
    public Graph(String title) {
        super(title);
        setContentPane(createPanel());
    }

    public JPanel createPanel() {
        return new ChartPanel(create(createDataset()));
    }

    private CategoryDataset createDataset() {
        var dataset = new DefaultCategoryDataset();
        var devices = Csv.read("C:\\ProjectUlearn\\src\\main\\resources\\devices.csv");
        for (var d:
                devices) {
            dataset.addValue(d.getWeight(), d.getName(), "");
        }
        return dataset;
    }

    private JFreeChart create(CategoryDataset dataset) {
        var jFreeChart = ChartFactory.createBarChart(
                "Зависимость веса устройства от его диагонали",
                null,
                "Вес",
                dataset);
        jFreeChart.setBackgroundPaint(Color.white);
        return jFreeChart;
    }

    public static void get() {
        var graphic = new Graph("Устройства");
        graphic.pack();
        graphic.setVisible(true);
    }
}
