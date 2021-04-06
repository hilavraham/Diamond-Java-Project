package unittest;
import static java.lang.System.out;
import static org.junit.Assert.*;
import static primitives.Util.isZero;

import org.junit.Test;

import primitives.Vector;

/**
 * Unit tests for primitives.Vector class
 */

/**
 * @author hilaa
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
    public void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals("Wrong vector add",new Vector(1, 1, 1),
                new Vector(2, 3, 4).add(new Vector(-1, -2, -3)));

        // =============== Boundary Values Tests ==================
        // TC11: test adding v + (-v)
		try { 
			new Vector(1, 2, 3).add(new Vector(-1, -2, -3));
			fail("Add v plus -v must throw exception");	
		}
		catch (IllegalArgumentException e) {}
    }

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	   @Test
	   public void  testSubtract() 
	   {
	        // ============ Equivalence Partitions Tests ==============
	        // TC01: Simple test
	        assertEquals(  "Wrong vector subtract",
	                new Vector(1, 1, 1),
	                new Vector(2, 3, 4).subtract(new Vector(1, 2, 3)));
	        
	        // =============== Boundary Values Tests ==================
	        // TC11: test subtract v - (v)
			try { 
				new Vector(1, 2, 3).subtract(new Vector(1, 2, 3));
				fail("subtract v from v must throw exception");	
			}
			catch (IllegalArgumentException e) {}
	    }
	   
	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		// ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals("Wrong vector scale",new Vector(2, 4, 6),
                new Vector(1, 2, 3).scale(2));
        // =============== Boundary Values Tests ==================
        // TC11: test scaling to 0
		try { 
			new Vector(1, 2, 3).scale(0);
			fail("Scale by 0 must throw exception");	
		}
		catch (IllegalArgumentException e) {}
		
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		 Vector v1 = new Vector(1, 2, 3);
		 Vector v3 = new Vector(0, 3, -2);
		 Vector v2 = new Vector(-2, -4, -6);
	        // ============ Equivalence Partitions Tests ==============
	        // TC01: Simple dotProduct test
	        assertEquals( "dotProduct() wrong value",-28d, v1.dotProduct(v2), 0.00001);
	        
	        // =============== Boundary Values Tests ==================
	        // TC11: dotProduct for orthogonal vectors
			try { 
				assertEquals( "dotProduct() for orthogonal vectors is not zero",
						0d, v1.dotProduct(v3),0.00001);	
			}
			catch (IllegalArgumentException e) {}
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		 Vector v1 = new Vector(1, 2, 3);
		 Vector v3 = new Vector(0, 3, -2);
		 Vector v2 = new Vector(-2, -4, -6);
		 
        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length",v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() wrong result length",isZero(vr.length() - v1.length() * v3.length()));
   
        assertTrue("crossProduct() result is not orthogonal to 2nd operand",isZero(vr.dotProduct(v1)));

     // =============== Boundary Values Tests ==================
        // =============== Boundary Values Tests ==================
        // TC11:test zero vector from cross-productof co-lined vectors
		try { 
	          v1.crossProduct(v2);	
	          fail("crossProduct() for parallel vectors does not throw an exception");
		}
		catch (IllegalArgumentException e) {}
	}
	

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
	      // ============ Equivalence Partitions Tests ==============
        // TC01: Simple test
        assertEquals("lengthSquared() wrong value",14d,
                new Vector(1, 2, 3).lengthSquared(), 0.00001
                );
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		 assertEquals( "length() wrong value",5d, new Vector(0, 3, 4).length(),0.00001);
	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		  Vector v = new Vector(0, 3, 4);
	        Vector n = v.normalized();
	        // ============ Equivalence Partitions Tests ==============
	        // TC01: Simple test
	        assertFalse( "normalized() changes the vector itself",v == n);
	        assertEquals( "wrong normalized vector length",1d, n.lengthSquared(), 0.00001);
	        assertThrows("normalized vector is not in the same direction"
	        		,IllegalArgumentException.class,
	                () -> v.crossProduct(n));
	        assertEquals( "wrong normalized vector",new Vector(0, 0.6, 0.8), n);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
	      Vector v = new Vector(3.5, -5, 10);
	        v.normalize();
	        assertEquals(1, v.length(), 1e-10);

	        assertThrows( "head camnot be (0,0,0)",
	        		IllegalArgumentException.class,
	                () -> new Vector(0, 0, 0));	             
	}
}
