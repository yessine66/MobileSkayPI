/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Question;
import com.mycompany.services.ServiceQuestion;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListeQuestionForm extends BaseForm{
    Form current;
    public ListeQuestionForm(Resources res)
    {
        
        
        super("Ajout Question",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Question");
        getContentPane().setScrollVisible(false);
        
     
        tb.addSearchCommand(s -> {   
            String text = (String) s.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4
 
         );
       
        
        
        
        
        Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("quiz.png"),"","",res);
        
        
        ///********************************************************/
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Questions", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
      
       
        
     ArrayList <Question>list =ServiceQuestion.getInstance().afficherQuestion();
 
  
 for (Question promo: list )
 {
     String urlImage="quiz.png";
     Image placeHolder =Image.createImage(12,90);
     EncodedImage enc= EncodedImage.createFromImage(placeHolder, false);
     URLImage urlimg=URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             // addButton(null,promo.getCodeP(),promo.getReduction(),promo.getDated(),promo.getDatef());
    // addButton(null,promo.getCodeP(),promo.getReduction(),promo.getDated(),promo.getDatef());
   // addButton(urlimg,promo.getCodeP(),promo.getReduction(),promo.getDated(),promo.getDatef(),promo,res);
   addButton(urlimg,promo,res);
   
     ScaleImageLabel image = new ScaleImageLabel(urlimg);
     Container containerImg= new Container();
     image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

   
   
   

 }
   
    }
         
     private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min (Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        if (image.getHeight()< size)
        {
            image = image.scaledHeight(size);
            
            
            
        }
      if (image.getHeight()> Display.getInstance().getDisplayHeight() / 2)
      {  
          image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);    
      
      }
      
      ScaleImageLabel imageScale = new ScaleImageLabel(image);
      imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
         Label overLay = new Label ("","ImageOverlay");
         Container page1=
                 LayeredLayout.encloseIn(
                 
                 imageScale,
                         overLay,
                                 BorderLayout.south(
                                         BoxLayout.encloseY(
                                                 new SpanLabel (text,"LargeWhiteText"),
                                                 //nooo

                                         spacer
                                         
                                         
                                 )
                 )
                 
                 
                 );
                 
                 
      swipe.addTab ("",res.getImage("quiz.png"),page1);
      
      
      
    }
    
    
    public void bindButtonSelection  (Button btn , Label l)
    {
        btn.addActionListener(e-> 
        
        
        { 
            if (btn.isSelected())
            { 
                updateArrowPosition(btn,l);
            }
        }
        
        
        
        
        //appel fct aaff
               // ServiceQuestion serviceQuestion = new ServiceQuestion();
       //    ArrayList <Question>list =ServiceQuestion.getInstance().afficherQuestion();
        
        
        
        
        );
    }

 
 
 
 
 
    private void updateArrowPosition(Button btn, Label l) {
     l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
     l.getParent().repaint();;
           
        
        
    }
  private void addButton(Image img, Question promo,Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f);
            int width= Display.getInstance().convertToPixels(14f);
            
            Button image = new Button(img.fill(width,height));
            
            image.setUIID("Label");
            
      //  Container cnt= new Container ();
      Container cnt = BorderLayout.west(image);
       // Container o = new Container ();
      
  /*   TextArea ta = new TextArea (codeP);
     ta.setUIID("NewsTopLine");
     ta.setEditable (false);*/
   Label idQ = new Label ("id: "+promo.getIdQ(),"NewsTopLine2");
  Label textQ = new Label ("La question: "+promo.getTextQ(),"NewsTopLine2");
  Label nbrPoint = new Label ("score: "+promo.getNbrPoint(),"NewsTopLine2");
  Label nameT = new Label ("("+promo.getNameT(),"NewsTopLine2");
 
  createLineSeparator();
     
     
// cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(codeText)/*,BoxLayout.encloseX(reductionText)*/,BoxLayout.encloseX(datedText),BoxLayout.encloseX(datefText)));
// cnt.add(BoxLayout.encloseX(datefText));
//o.add(BorderLayout.CENTER);
 // cnt.addAll(BoxLayout.encloseY(BoxLayout.encloseX(codeText),BoxLayout.encloseX(reductionText)),BoxLayout.encloseY(BoxLayout.encloseX(datedText),BoxLayout.encloseX(datefText)));
 
 
 
 //supprimer btn
 Label lsupprimer = new Label (" ");
 
 lsupprimer.setUIID ("NewsTopLine");
 Style supprimerStyle = new Style (lsupprimer.getUnselectedStyle());
 supprimerStyle.setFgColor(0xf21f21);
 
 
 FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
 lsupprimer.setIcon(supprimerImage);
 lsupprimer.setTextPosition(RIGHT);
 
 //clixk delete btn
 lsupprimer.addPointerPressedListener(l -> {

Dialog dig = new Dialog ("suppression");
if (dig.show("Suppression","Voulez vous supprimer cette question ?","annuler","Oui"))
{ 

dig.dispose();

}

else 
{ 
    dig.dispose();
    //appel fct supp service
    if (ServiceQuestion.getInstance().supprimerQuestion(promo.getIdQ()))
    { 
    
    
 new ListeQuestionForm(res).show();
    
    
    
    
    
    }
    
}




 });
 

 //update icon 
 
 Label lmodifier = new Label (" ");
 
 lmodifier.setUIID ("NewsTopLine");
 Style modifierStyle = new Style ( lmodifier.getUnselectedStyle());
modifierStyle.setFgColor(0xf7ad02);
 
 FontImage modifierImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
lmodifier.setIcon(modifierImage);
lmodifier.setTextPosition(LEFT);
 
 

//clixk updatze btn
 lmodifier.addPointerPressedListener(l -> {


new ModifierQuestionForm(res,promo).show();

 });
 



 
 
 
 cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
         
         
        //BoxLayout.encloseX(idQ,idQ),
         // BoxLayout.encloseX(datedText),
          // BoxLayout.encloseX(datefText),
           BoxLayout.encloseX(textQ,idQ) ,
         BoxLayout.encloseX(nbrPoint,lmodifier,lsupprimer)) );
 
 
 
 
 
 
 
 
 
 
     add(cnt);
   
    }
    
 
    
    

 
        
        
        
    }
    
    
     
    
    
    
    
    
    
    
    
    
    
    
    
