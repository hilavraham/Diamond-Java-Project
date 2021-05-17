package elements;

import primitives.Color;

public class AmbientLight {
public Color intensity; 

	public AmbientLight(Color color,double ka)
	{
		intensity=color.scale(ka);
	}

	public Color get_intensity() 
	{
		return intensity;
	}
	

}
