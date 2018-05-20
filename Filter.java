import java.awt.Graphics;

/**
 * Filter is an abstract superclass for all image filters in this
 * application. Filters can be applied to OFImages by invoking the apply 
 * method.
 */
public abstract class Filter
{
    private String name;

    /**
     * Create a new filter with a given name.
     */
    public Filter(String name)
    {
        this.name = name;
    }
    
    /**
     * Return the name of this filter.
     * 
     * @return  The name of this filter.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public abstract void apply(OFImage image);
    
 
}