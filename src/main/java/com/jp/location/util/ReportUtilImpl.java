package com.jp.location.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.stereotype.Component;

@Component
public class ReportUtilImpl implements ReportUtil {

	public void generatePieChart(String path, List<Object[]> data) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		for (Object[] object : data) {
			dataSet.setValue(object[0].toString(), new Double(object[10].toString()));
		}
		JFreeChart chart = ChartFactory.createPieChart3D("Location type report", dataSet);
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/pieChart.jpeg"), chart, 300, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
