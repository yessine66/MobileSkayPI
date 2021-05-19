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
import com.mycompany.gui.AjouterPartenaire;
//import com.mycompany.gui.AjouterPromotion;
import com.mycompany.services.ServicePartenaire;
import com.mycompany.entities.Partenaire;
import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class CatDomaine   extends BaseForm {
private ArrayList<Partenaire> partenaires;
    Form current;
    
    public CatDomaine(Resources res)
    {              super.addSideMenu (res);

        /**************************/
        Label al = new Label("Alimentaion");
      Label ed = new  Label("Educcation");
     Label st = new  Label("Sante");
        Form f=createPieChartForm();
          this.addAll(ed,st,al);
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

      series.add("Sante" ,values[0]);
    series.add("Education" ,values[1]);
     series.add("Alimenataion" ,values[2]);
         


    return series;
}

public Form createPieChartForm() {
int ali=0;
int sante =0;
int edu=0;
/*String s1="str";
String s2="str";
if(s1.equals(s2))
*/

    System.out.println("chbik!!");
     partenaires = new ServicePartenaire().afficherPartenaire();
  for (Partenaire p : partenaires) {
          if(p.getDomaine().equals("alimentation"))
{
ali ++;
    System.out.println("ali"+ali+p.getDomaine());
}
          else  if (p.getDomaine().equals("education"))
          {
              edu++;
               System.out.println("edu"+edu+p.getDomaine()   );
          }
          else{
              sante++;
           System.out.println("sante"+sante+p.getDomaine());}
            
        }  


    // Generate the values
  //  double[] values = new double[]{12, 14, 11, 10, 19};
    double[] values = new double[]{ali,edu,sante};
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
