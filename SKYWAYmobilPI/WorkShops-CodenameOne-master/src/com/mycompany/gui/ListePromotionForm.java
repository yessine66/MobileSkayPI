/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
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
import com.mycompany.entities.Promotion;
import com.mycompany.services.ServicePromotion;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ListePromotionForm extends BaseForm{
    Form current;
    public ListePromotionForm(Resources res)
    {
        
        
        super("Ajout Promotion",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Promotion");
        getContentPane().setScrollVisible(false);
                   super.addSideMenu (res);

        
        
        tb.addSearchCommand(s -> { 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        } );
        
        
        
        
        
        Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("nour.png"),"","",res);
        
        
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
        RadioButton mesListes = RadioButton.createToggle("Ajputer promotion", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Mes promotions", barGroup);
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

       
        
     ArrayList <Promotion>list =ServicePromotion.getInstance().afficherPromotion();
 
  
 
 for (Promotion promo: list )
 {
     String urlImage="nour.png";
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
                 
                 
      swipe.addTab ("",res.getImage("nour.png"),page1);
      
      
      
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
               // ServicePromotion servicePromotion = new ServicePromotion();
       //    ArrayList <Promotion>list =ServicePromotion.getInstance().afficherPromotion();
        
        
        
        
        );
    }

 
 
 
 
 
 
 
 
 
 
 
 
 
    private void updateArrowPosition(Button btn, Label l) {
     l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
     l.getParent().repaint();;
           
        
        
    }
//   private void addButton(Image img ,String codeP, int reduction,String dated,String datef,Promotion promo,Resources res)
    private void addButton(Image img, Promotion promo,Resources res) {
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
   Label idPromo = new Label ("idP: "+promo.getIdProm(),"NewsTopLine2");
  Label codeText = new Label ("Code promo: "+promo.getCodeP(),"NewsTopLine2");
  Label reductionText = new Label ("Reduction: "+promo.getReduction(),"NewsTopLine2");
  Label datedText = new Label ("("+promo.getDated(),"NewsTopLine2");
  Label datefText = new Label ("-"+promo.getDatef()+" )","NewsTopLine2");
  // Label idP = new Label ("Partenaire"+promo.getIdP(),"NewsTopLine2");
    Label idP = new Label ("Partenaire: "+promo.getNom(),"NewsTopLine2");
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
if (dig.show("Suppression","Voulez vous supprimer cette promotion ?","annuler","Oui"))
{ 

dig.dispose();

}

else 
{ 
  //dig.dispose();
    //appel fct supp service
    if (ServicePromotion.getInstance().supprimerPromotion(promo.getIdProm()))
    { 
       
                 ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("checked.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Promotion supprimé avec succès");
                                                  status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
    
 new ListePromotionForm(res).show();
    
    
    
    
    
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


new ModifierPromotionForm(res,promo).show();

 });
 
 



























 
 
 
 cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
         
         
         BoxLayout.encloseX(idPromo,reductionText),
         // BoxLayout.encloseX(datedText),
          // BoxLayout.encloseX(datefText),
           BoxLayout.encloseX(datedText,datefText,idP) ,
         BoxLayout.encloseX(codeText,lmodifier,lsupprimer)) );
 
 
 
 
 
 
 
 
 
 
     add(cnt);
   
    }
    
 
    
    
    
    
    
    
    
    
    
}
