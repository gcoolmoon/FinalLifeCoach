package unitn.lifecoach.model;

public class RecipePojo {
    
private Recipe recipe;

public Recipe getRecipe ()
{
return recipe;
}

public void setRecipe (Recipe recipe)
{
this.recipe = recipe;
}

@Override
public String toString()
{
return "ClassPojo [recipe = "+recipe+"]";
}
}


