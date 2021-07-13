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
    //for Reflection
	public double kr=0;
	//for  Refraction (Transparency)
	public double kt=1; 

    /**
	 * @return the kr
	 */
	public double getKr() {
		return kr;
	}

	/**
	 * @param kr the kr to set
	 */
	public Material setKr(double kr) {
		this.kr = kr;
        return this;
	}

	/**
	 * @return the kt
	 */
	public double getKt() {
		return kt;
	}

	/**
	 * @param kt the kt to set
	 */
	public Material setKt(double kt) {
		this.kt = kt;
		 return this;
	}

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
