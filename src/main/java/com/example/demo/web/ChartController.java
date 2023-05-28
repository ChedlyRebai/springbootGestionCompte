package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChartController {

    @GetMapping("/chart")
    public String showChart(Model model) {
        List<ChartData> pieChartData = getPieChartData();
        model.addAttribute("pieChartData", pieChartData);
        return "chart";
    }

    private List<ChartData> getPieChartData() {
        // Retrieve the pie chart data from your data source or service
        List<ChartData> data = new ArrayList<>();
        data.add(new ChartData("Category 1", 30, "#FF6384"));
        data.add(new ChartData("Category 2", 50, "#36A2EB"));
        data.add(new ChartData("Category 3", 20, "#FFCE56"));
        return data;
    }

    // Inner class representing the chart data
    public static class ChartData {
        private String label;
        private int value;
        private String color;

        public ChartData(String label, int value, String color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        // Getters and setters
    }
}
