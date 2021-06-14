/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author hilaa
 *
 */
public abstract class Light {
	
	/**
     * intensity of ambient light color
     */

    final protected Color intensity;
    
    /**
     * Constructor
     * @param Ia intensity color
     * */
	public Light(Color _intensity) {
		this.intensity = _intensity;
	}

	/**
	 * @return the intensity
	 */
	public Color getIntensity() {
		return intensity;
	}

}
