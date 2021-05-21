/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;

import com.mycompany.services.ServiceQuestion;
import com.mycompany.entities.Question;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class StatQ   extends BaseForm {
private ArrayList<Question> questions;
    Form current;
    
    public StatQ(Resources res)
    {              super.addSideMenu (res);

        /**************************/
        Label al = new Label("SVT");
      Label ed = new  Label("POO");
     Label st = new  Label("MATHS");
     Label ph = new  Label("PHYSIQUE");
        Form f=createPieChartForm();
          this.addAll(ed,st,al,ph);
        this.add(f);
        
        
 // this.addSideMenu (res);  
        
        
        
        
        
        
        /*******************************************************************/
    
    }
        
/*******************/
   /**
 * Creates a renderer for the specified colors.
 */
private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(50);
    renderer.setLegendTextSize(50);
          int[] colo = new int[]{ColorUtil.BLACK};
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);

      series.add("SVT" ,values[0]);
    series.add("POO" ,values[1]);
     series.add("MATHS" ,values[2]);
           series.add("PHYSIQUE" ,values[3]);
   


    return series;
}

public Form createPieChartForm() {
int svt=0;
int phys =0;
int poo=0;
int MATHS=0;

/*String s1="str";
String s2="str";
if(s1.equals(s2))
*/

 
     questions = new ServiceQuestion().afficherQuestion();
  for (Question p : questions) {
          if(p.getNameT().equals("SVT"))
{
svt ++;
    System.out.println("svt"+svt+p.getNameT());
}
          else  if (p.getNameT().equals("POO"))
          {
              poo++;
               System.out.println("poo"+poo+p.getNameT()   );
          }
           else  if (p.getNameT().equals("MATHS"))
          {
              MATHS++;
               System.out.println("MATHS"+MATHS+p.getNameT()   );
          } else{
              phys++;
           System.out.println("phys"+phys+p.getNameT());}
            
        }  


    // Generate the values
  //  double[] values = new double[]{12, 14, 11, 10, 19};
    double[] values = new double[]{svt,poo,MATHS,phys};
    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.YELLOW, ColorUtil.CYAN};

    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);
    
    // Create the chart ... pass the values and renderer to the chart object.
  //  PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
 PieChart chart = new PieChart(buildCategoryDataset("Project budget", values), renderer);
    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Budget");
    f.setLayout(new BorderLayout());
    f.addComponent(BorderLayout.CENTER, c);
    return f;

}
   /****************/
/*


protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    /*
    int k = 0;
    for (double value : values) {
        series.add("Nombre " + ++k, value);
    }
    */
 /*   series.add("non traite" ,values[0]);
    series.add("traite" ,values[1]);
    return series;



*/
    
}
