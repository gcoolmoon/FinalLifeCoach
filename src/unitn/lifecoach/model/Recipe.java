package unitn.lifecoach.model;


                
public class Recipe
{
   private String[] ingredients;

   private String title;

   private String publisher_url;

   private String image_url;

   private String social_rank;

   private String source_url;

   private String f2f_url;

   private String recipe_id;

   private String publisher;

   public String[] getIngredients ()
   {
       return ingredients;
   }

   public void setIngredients (String[] ingredients)
   {
       this.ingredients = ingredients;
   }

   public String getTitle ()
   {
       return title;
   }

   public void setTitle (String title)
   {
       this.title = title;
   }

   public String getPublisher_url ()
   {
       return publisher_url;
   }

   public void setPublisher_url (String publisher_url)
   {
       this.publisher_url = publisher_url;
   }

   public String getImage_url ()
   {
       return image_url;
   }

   public void setImage_url (String image_url)
   {
       this.image_url = image_url;
   }

   public String getSocial_rank ()
   {
       return social_rank;
   }

   public void setSocial_rank (String social_rank)
   {
       this.social_rank = social_rank;
   }

   public String getSource_url ()
   {
       return source_url;
   }

   public void setSource_url (String source_url)
   {
       this.source_url = source_url;
   }

   public String getF2f_url ()
   {
       return f2f_url;
   }

   public void setF2f_url (String f2f_url)
   {
       this.f2f_url = f2f_url;
   }

   public String getRecipe_id ()
   {
       return recipe_id;
   }

   public void setRecipe_id (String recipe_id)
   {
       this.recipe_id = recipe_id;
   }

   public String getPublisher ()
   {
       return publisher;
   }

   public void setPublisher (String publisher)
   {
       this.publisher = publisher;
   }

   @Override
   public String toString()
   {
       return "ClassPojo [ingredients = "+ingredients+", title = "+title+", publisher_url = "+publisher_url+", image_url = "+image_url+", social_rank = "+social_rank+", source_url = "+source_url+", f2f_url = "+f2f_url+", recipe_id = "+recipe_id+", publisher = "+publisher+"]";
   }
}
			
			