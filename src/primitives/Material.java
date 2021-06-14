package primitives;

/**
 * Material class - represent the material properties of the body
 */
public class Material {
    //Discount coefficients with default values
	// for diffuse
    public double kD = 0;
    // for specular
    public double kS = 0; 
    // shininess
    public int nShininess = 0; 

    /**
     * set function for kD
     * @param kD for diffuse
     * @return the object
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set function for kS
     * @param kS specular
     * @return the object
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set for shininess
     * @param nShininess
     * @return the object
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}